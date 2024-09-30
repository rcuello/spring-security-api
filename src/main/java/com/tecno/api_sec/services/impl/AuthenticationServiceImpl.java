package com.tecno.api_sec.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.tecno.api_sec.controllers.api.dtos.SaveUserDTO;
import com.tecno.api_sec.controllers.api.dtos.auth.AuthenticationRequestDTO;
import com.tecno.api_sec.controllers.api.dtos.auth.AuthenticationResponseDTO;
import com.tecno.api_sec.controllers.api.dtos.responses.RegisteredUserDTO;
import com.tecno.api_sec.persistence.entity.UserEntity;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    public RegisteredUserDTO registerOneCustomer(SaveUserDTO newUser){
        
        UserEntity user = userService.registerOneCustomer(newUser);

        RegisteredUserDTO userDTO = new RegisteredUserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getFirstName());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole().name());

        String jwt = jwtService.generateToken(user,generateExtraClaims(user));
        userDTO.setJwt(jwt);
        
        return userDTO;
    }

    private Map<String, Object> generateExtraClaims(UserEntity user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name",user.getFirstName());
        extraClaims.put("role",user.getRole().name());
        extraClaims.put("authorities",user.getAuthorities());

        return extraClaims;
    }

    public AuthenticationResponseDTO login(AuthenticationRequestDTO authRequest){

        String username = authRequest.getUsername();
        String password = authRequest.getPassword();

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

        authenticationManager.authenticate(authentication);

        UserDetails user = userService.findOneByUsername(username).get();
        UserEntity userEntity = (UserEntity) user;

        String jwt = jwtService.generateToken(user, generateExtraClaims(userEntity));

        AuthenticationResponseDTO authRsp = new AuthenticationResponseDTO();
        authRsp.setJwt(jwt);

        return authRsp;
    }

    public boolean validateToken(String jwt) {
        try {
            return jwtService.validateToken(jwt);
        } catch (Exception e) {
            System.err.println("Error al validar JWT Token: " + e.getMessage());
            return false;
        }
    }
}
