package com.tecno.api_sec.persistence.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecno.api_sec.persistence.entity.UserEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;


@Service
public class UserDaoImpl implements IUserDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return entityManager.createQuery("SELECT u From UserEntity u").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(UserEntity.class, id));
    }

    @Override
    @Transactional
    public void saveUser(UserEntity userEntity) {
        entityManager.persist(userEntity);
        entityManager.flush();
    }

    @Override
    @Transactional
    public void updateUser(UserEntity userEntity) {
        entityManager.merge(userEntity);
        //entityManager.flush();
    }

    @Override
    @Transactional
    public void deleteUser(UserEntity userEntity) {
        entityManager.remove(userEntity);
        //entityManager.flush();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findByUsername(String username) {
        try {
            UserEntity user = entityManager.createQuery(
                    "SELECT u FROM UserEntity u WHERE u.username = :username", UserEntity.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return Optional.ofNullable(user);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}
