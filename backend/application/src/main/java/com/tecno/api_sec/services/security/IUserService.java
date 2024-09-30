package com.tecno.api_sec.services.security;



import com.tecno.api_sec.controllers.api.dtos.SaveUserDTO;
import com.tecno.api_sec.persistence.entity.UserEntity;
import java.util.Optional;

public interface IUserService {
    UserEntity registerOneCustomer(SaveUserDTO newUser);

    Optional<UserEntity> findOneByUsername(String username);
}
