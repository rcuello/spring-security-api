package com.ecommerce.ecommerce.model.dao;

import java.util.Optional;

import com.ecommerce.ecommerce.model.persistence.UserEntity;

public interface UserDAO {
    Optional<UserEntity> findByUsername(String username);
    void saveUser(UserEntity userEntity);
} 
