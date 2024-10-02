package com.ecommerce.ecommerce.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ecommerce.ecommerce.controllers.dto.CustomerCreateNewDTO;
import com.ecommerce.ecommerce.controllers.dto.CustomerCreatedDTO;
import com.ecommerce.ecommerce.exceptions.InvalidPasswordException;
import com.ecommerce.ecommerce.model.Role;
import com.ecommerce.ecommerce.model.dao.UserDAO;
import com.ecommerce.ecommerce.model.persistence.UserEntity;
import com.ecommerce.ecommerce.services.security.JwtService;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterCustomerService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public CustomerCreatedDTO registerOneCustomer(CustomerCreateNewDTO newCustomer){
        
        //Validacion de contraseñas
        validatePassword(newCustomer);

        UserEntity user = new UserEntity();
        String passwordEncoded = passwordEncoder.encode(newCustomer.getPassword());

        user.setUsername(newCustomer.getUsername());
        user.setEmail(newCustomer.getEmail());
        user.setFirstName(newCustomer.getName());
        user.setLastName(newCustomer.getLastName());
        user.setPassword(passwordEncoded);
        user.setRole(Role.CUSTOMER);

        //Guardar usuario en la base de datos
        userDAO.saveUser(user);

        CustomerCreatedDTO userDTO = new CustomerCreatedDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getFirstName());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole().name());

        //Genera claims adicionales para incluir en el token JWT.
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getFirstName());
        extraClaims.put("role", user.getRole().name());
        extraClaims.put("authorities", user.getAuthorities());

        // Genera un token JWT para el usuario registrado
        String jwt = jwtService.generateToken(user, extraClaims);
        userDTO.setJwt(jwt);

        return userDTO;
    }

    private void validatePassword(CustomerCreateNewDTO newUser){
        String password1= newUser.getPassword();
        String password2 = newUser.getRepeatedPassword();

        if(!StringUtils.hasText(password1) || !StringUtils.hasText(password2)){
            throw new InvalidPasswordException ("Se deben proporcionar ambas contraseñas");
        }

        if(!password1.equals(password2)){
            throw new InvalidPasswordException("Las contraseñas no coinciden");
        }
    }
}
