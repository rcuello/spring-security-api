package com.tecno.api_sec.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tecno.api_sec.persistence.dao.IUserDAO;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private IUserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.findByUsername(username)
                              .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    }

}
