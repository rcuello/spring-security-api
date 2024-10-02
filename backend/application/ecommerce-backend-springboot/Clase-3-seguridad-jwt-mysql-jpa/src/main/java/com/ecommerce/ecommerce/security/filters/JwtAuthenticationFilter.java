package com.ecommerce.ecommerce.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ecommerce.ecommerce.exceptions.UserNotFoundException;
import com.ecommerce.ecommerce.model.dao.UserDAO;
import com.ecommerce.ecommerce.model.persistence.UserEntity;
import com.ecommerce.ecommerce.services.security.JwtService;

/**
 * Filtro que se ejecuta una vez por cada solicitud para autenticar al usuario utilizando un token JWT.
 * 
 * Este filtro se encarga de extraer el token JWT del encabezado "Authorization" de la solicitud,
 * validar el token, obtener los detalles del usuario y establecer la autenticación en el contexto de seguridad.
 * 
 * @author Ronald Cuello
 * @version 1.0
 * @since 2024-10-01
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private JwtService jwtService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
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
            UserEntity user = userDAO.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found. Username: " + username));

            // 5. Crear y establecer el objeto de autenticación en el contexto de seguridad
            setAuthentication(request, user);
        }

        // 6. Continuar con el siguiente filtro en la cadena
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.split(" ")[1];
        }
        return null;
    }

    /**
     * Crea y establece el objeto de autenticación en el contexto de seguridad.
     * 
     * Este método crea un objeto de autenticación utilizando el nombre de usuario y las autoridades del usuario,
     * y lo establece en el contexto de seguridad.
     * 
     * @param request La solicitud HTTP recibida.
     * @param user    Los detalles del usuario.
     */
    private void setAuthentication(HttpServletRequest request, UserEntity user) {

        // 1. Crear un objeto de autenticación con el nombre de usuario y las autoridades del usuario
        // UsernamePasswordAuthenticationToken es un tipo de objeto de autenticación utilizado en Spring Security
        // que representa la autenticación del usuario.
        // user.getUsername(): Obtiene el nombre de usuario del objeto UserEntity.
        // null: No se necesita una contraseña en este caso, ya que el usuario ya está autenticado.
        // user.getAuthorities(): Obtiene las autoridades (roles y permisos) del usuario.
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), null, user.getAuthorities()
        );

        // 2. Establecer detalles adicionales en el objeto de autenticación
        // WebAuthenticationDetails es una clase que encapsula detalles específicos de la solicitud HTTP.
        // new WebAuthenticationDetails(request): Crea una instancia de WebAuthenticationDetails con la solicitud HTTP.
        // authToken.setDetails(webAuthDetails): Establece los detalles de autenticación web en el objeto de autenticación.
        // Estos detalles pueden incluir información sobre la solicitud HTTP, como la dirección IP del cliente, la sesión del usuario, etc.
        WebAuthenticationDetails webAuthDetails = new WebAuthenticationDetails(request);
        authToken.setDetails(webAuthDetails);

        // 3. Establecer detalles de auditoría
        // AuditDetails es una clase que encapsula detalles de auditoría, como la dirección IP del cliente y el agente de usuario.
        // new AuditDetails(request.getRemoteAddr(), request.getHeader("User-Agent")): Crea una instancia de AuditDetails
        // con la dirección IP remota y el agente de usuario de la solicitud HTTP.
        // authToken.setDetails(auditDetails): Establece los detalles de auditoría en el objeto de autenticación.
        AuditDetails auditDetails = new AuditDetails(request.getRemoteAddr(), request.getHeader("User-Agent"));
        authToken.setDetails(auditDetails);


        // 4. Establecer el objeto de autenticación en el contexto de seguridad
        // SecurityContextHolder.getContext().setAuthentication(authToken): Establece el objeto de autenticación
        // en el contexto de seguridad, lo que significa que el usuario está autenticado en la aplicación.
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

}
