package com.tecno.api_sec.configuration.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tecno.api_sec.exceptions.ObjectNotFoundException;
import com.tecno.api_sec.persistence.entity.UserEntity;
import com.tecno.api_sec.services.impl.JwtService;
import com.tecno.api_sec.services.impl.UserServiceImpl;
import java.io.IOException;

/**
 * Filtro que se ejecuta una vez por cada solicitud para autenticar al usuario utilizando un token JWT.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserServiceImpl userService;

    /**
     * Método que se ejecuta una vez por cada solicitud para autenticar al usuario utilizando un token JWT.
     * 
     * @param request       La solicitud HTTP recibida.
     * @param response      La respuesta HTTP que se enviará.
     * @param filterChain   La cadena de filtros que se ejecutará después de este filtro.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws IOException      Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("ENTRO EN EL FILTRO JWT AUTHENTICATION FILTER");

        // 1. Obtener el token JWT desde el encabezado "Authorization"
        String jwt = getJwtFromRequest(request);

        if (jwt == null) {
            // Si no cumple con las condiciones, continuar con el siguiente filtro en la cadena
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Obtener el subject/username desde el token JWT
        String username = jwtService.extractUsername(jwt);

        // 3. Verificar si el usuario ya está autenticado en el contexto de seguridad
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // 4. Obtener los detalles del usuario desde el servicio de usuarios
            UserEntity user = getUserFromUsername(username);

            // 5. Crear y establecer el objeto de autenticación en el contexto de seguridad
            setAuthentication(request, user);
        }

        // 6. Continuar con el siguiente filtro en la cadena
        filterChain.doFilter(request, response);
    }

    /**
     * Extrae el token JWT desde el encabezado "Authorization" de la solicitud.
     * 
     * @param request La solicitud HTTP recibida.
     * @return El token JWT o null si no se encuentra o no es válido.
     */
    private String getJwtFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.split(" ")[1];
        }
        return null;
    }

    /**
     * Obtiene los detalles del usuario desde el servicio de usuarios utilizando el nombre de usuario.
     * 
     * @param username El nombre de usuario.
     * @return Los detalles del usuario.
     * @throws ObjectNotFoundException Si el usuario no se encuentra.
     */
    private UserEntity getUserFromUsername(String username) {
        return userService.findOneByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User not found. Username: " + username));
    }

    /**
     * Crea y establece el objeto de autenticación en el contexto de seguridad.
     * 
     * @param request La solicitud HTTP recibida.
     * @param user    Los detalles del usuario.
     */
    private void setAuthentication(HttpServletRequest request, UserEntity user) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), null, user.getAuthorities()
        );
        authToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }


}
