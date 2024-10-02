package com.ecommerce.ecommerce.controllers.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Size;

public class CustomerCreateNewDTO implements Serializable{
    @Size(min = 4)
    private String name;

    @Size(min = 4)
    private String lastName;

    @Size(min = 4)
    private String username;
    
    @Size(min = 8)
    private String password;
    
    @Size(min = 8)
    private String repeatedPassword;

    @Size(min = 4)
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
