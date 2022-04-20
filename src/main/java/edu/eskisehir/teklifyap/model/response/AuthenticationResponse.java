package edu.eskisehir.teklifyap.model.response;

import lombok.Data;

//Authentication response
@Data
public class AuthenticationResponse {
    private long timestamp=System.currentTimeMillis();
    private String status;
    private String token;
    private String path;

    public AuthenticationResponse(String status, String token, String path) {
        this.status = status;
        this.token = token;
        this.path = path;
    }

}
