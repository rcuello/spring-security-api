package com.tecno.api_sec.controllers.api.dtos.auth;

import java.io.Serializable;

public class AuthenticationResponseDTO implements Serializable {
    private String jwt;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
