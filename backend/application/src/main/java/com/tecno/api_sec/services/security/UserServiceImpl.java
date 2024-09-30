package com.tecno.api_sec.services.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tecno.api_sec.controllers.api.dtos.SaveUserDTO;
import com.tecno.api_sec.exceptions.InvalidPasswordException;
import com.tecno.api_sec.persistence.dao.IUserDAO;
import com.tecno.api_sec.persistence.entity.Role;
import com.tecno.api_sec.persistence.entity.UserEntity;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity registerOneCustomer(SaveUserDTO newUser) {
        
        //Validacion de contraseñas
        validatePassword(newUser);

        //Validacion que no exista un usuario repetido

        UserEntity user = new UserEntity();

        String passwordEncoded = passwordEncoder.encode(newUser.getPassword());

        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setFirstName(newUser.getName());
        user.setLastName(newUser.getLastName());
        user.setPassword(passwordEncoded);
        user.setRole(Role.ROLE_CUSTOMER);

        //Guardar usuario en la base de datos
        userDAO.saveUser(user);

        return user;
    }

    private void validatePassword(SaveUserDTO newUser){
        String password1= newUser.getPassword();
        String password2 = newUser.getRepeatedPassword();

        if(!StringUtils.hasText(password1) || !StringUtils.hasText(password2)){
            throw new InvalidPasswordException ("Se deben proporcionar ambas contraseñas");
        }

        if(!password1.equals(password2)){
            throw new InvalidPasswordException("Las contraseñas no coinciden");
        }
    }


    @Override
    public Optional<UserEntity> findOneByUsername(String username) {
        return userDAO.findByUsername(username);
    }

}
