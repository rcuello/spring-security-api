package com.ecommerce.ecommerce.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.model.dao.UserDAO;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return userDAO.findByUsername(username)
                              .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    }

}
