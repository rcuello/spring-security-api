package com.tecno.api_sec.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.tecno.api_sec.controllers.api.dtos.SaveUserDTO;
import com.tecno.api_sec.controllers.api.dtos.auth.AuthenticationRequestDTO;
import com.tecno.api_sec.controllers.api.dtos.auth.AuthenticationResponseDTO;
import com.tecno.api_sec.controllers.api.dtos.responses.RegisteredUserDTO;
import com.tecno.api_sec.exceptions.ObjectNotFoundException;
import com.tecno.api_sec.persistence.entity.UserEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Servicio de autenticación que maneja la lógica de registro, inicio de sesión y validación de tokens JWT.
 * 
 * Esta clase proporciona métodos para registrar un nuevo usuario, autenticar un usuario existente,
 * validar un token JWT y encontrar el usuario actualmente autenticado.
 * 
 * @author Ronald Cuello
 * @version 1.0
 * @since 2024-10-01
 */
@Service
public class AuthenticationServiceImpl {

    /**
     * Servicio de usuario utilizado para registrar y buscar usuarios.
     */
    @Autowired
    private UserServiceImpl userService;

    /**
     * Servicio JWT utilizado para generar y validar tokens JWT.
     */
    @Autowired
    private JwtService jwtService;

    /**
     * Administrador de autenticación utilizado para autenticar usuarios.
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    
    /**
     * Registra un nuevo cliente en el sistema.
     * 
     * Este método registra un nuevo usuario en el sistema y genera un token JWT para el usuario registrado.
     * 
     * @param newUser Datos del nuevo usuario a registrar.
     * @return RegisteredUserDTO Objeto que contiene la información del usuario registrado y el token JWT.
     */
    public RegisteredUserDTO registerOneCustomer(SaveUserDTO newUser){
        
        // Registra el nuevo usuario en el sistema
        UserEntity user = userService.registerOneCustomer(newUser);

        // Crea un objeto RegisteredUserDTO para devolver la información del usuario registrado
        RegisteredUserDTO userDTO = new RegisteredUserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getFirstName());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole().name());

        // Genera un token JWT para el usuario registrado
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        userDTO.setJwt(jwt);
        
        return userDTO;
    }

    /**
     * Genera claims adicionales para incluir en el token JWT.
     * 
     * Este método genera un mapa de claims adicionales que se incluirán en el token JWT,
     * como el nombre del usuario, el rol y las autoridades.
     * 
     * @param user Usuario para el cual se generan los claims adicionales.
     * @return Map<String, Object> Mapa de claims adicionales.
     */
    private Map<String, Object> generateExtraClaims(UserEntity user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getFirstName());
        extraClaims.put("role", user.getRole().name());
        extraClaims.put("authorities", user.getAuthorities());

        return extraClaims;
    }

    /**
     * Autentica un usuario existente en el sistema.
     * 
     * Este método autentica un usuario existente en el sistema utilizando el nombre de usuario y la contraseña
     * proporcionados en la solicitud de autenticación. Si la autenticación es exitosa, genera un token JWT para el usuario.
     * 
     * @param authRequest Datos de la solicitud de autenticación.
     * @return AuthenticationResponseDTO Objeto que contiene el token JWT generado.
     */
    public AuthenticationResponseDTO login(AuthenticationRequestDTO authRequest){

        String username = authRequest.getUsername();
        String password = authRequest.getPassword();

        // Crea un objeto de autenticación con el nombre de usuario y la contraseña
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

        // Autentica al usuario utilizando el administrador de autenticación
        authenticationManager.authenticate(authentication);

        // Busca al usuario autenticado en el sistema
        UserDetails user = userService.findOneByUsername(username).get();
        UserEntity userEntity = (UserEntity) user;

        // Genera un token JWT para el usuario autenticado
        String jwt = jwtService.generateToken(user, generateExtraClaims(userEntity));

        // Crea un objeto AuthenticationResponseDTO para devolver el token JWT
        AuthenticationResponseDTO authRsp = new AuthenticationResponseDTO();
        authRsp.setJwt(jwt);

        return authRsp;
    }

    /**
     * Valida un token JWT.
     * 
     * Este método valida un token JWT utilizando el servicio JWT. Si el token es válido, devuelve true;
     * de lo contrario, devuelve false.
     * 
     * @param jwt Token JWT a validar.
     * @return boolean true si el token es válido, false en caso contrario.
     */
    public boolean validateToken(String jwt) {
        try {
            return jwtService.validateToken(jwt);
        } catch (Exception e) {
            System.err.println("Error al validar JWT Token: " + e.getMessage());
            return false;
        }
    }

    /**
     * Encuentra el usuario actualmente autenticado.
     * 
     * Este método encuentra el usuario actualmente autenticado en el sistema utilizando el contexto de seguridad.
     * Si el usuario no se encuentra, lanza una excepción ObjectNotFoundException.
     * 
     * @return UserEntity Usuario actualmente autenticado.
     * @throws ObjectNotFoundException Si el usuario no se encuentra en el sistema.
     */
    public UserEntity findCurrentUser() {

        // Obtiene el objeto de autenticación del contexto de seguridad
        UsernamePasswordAuthenticationToken auth =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        // Obtiene el nombre de usuario del objeto de autenticación
        String username = (String) auth.getPrincipal();

        // Busca al usuario en el sistema utilizando el nombre de usuario
        return userService.findOneByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User not found. Username: " + username));
    }
}