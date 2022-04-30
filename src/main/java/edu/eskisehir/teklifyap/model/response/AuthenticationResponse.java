package edu.eskisehir.teklifyap.model.response;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private long timestamp = System.currentTimeMillis();
    private String status;
    private String token;
    private int userID;
    private String path;

    public AuthenticationResponse(String status, String token, int userID, String path) {
        this.status = status;
        this.token = token;
        this.userID = userID;
        this.path = path;
    }

}
