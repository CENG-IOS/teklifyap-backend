package edu.eskisehir.teklifyap.model.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String mail;
    private String password;
    private boolean rememberMe;
}
