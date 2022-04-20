package edu.eskisehir.teklifyap.model.request;

import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String surname;
    private String mail;
    private String password;
}
