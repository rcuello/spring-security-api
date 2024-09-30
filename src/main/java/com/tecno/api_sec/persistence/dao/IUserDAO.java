package com.tecno.api_sec.persistence.dao;

import java.util.List;
import java.util.Optional;

import com.tecno.api_sec.persistence.entity.UserEntity;

public interface IUserDAO {
    List<UserEntity> findAll();
    Optional<UserEntity> findById(Long id);
    Optional<UserEntity> findByUsername(String username);
    void saveUser(UserEntity userEntity);
    void updateUser(UserEntity userEntity);
    void deleteUser(UserEntity userEntity);
}
