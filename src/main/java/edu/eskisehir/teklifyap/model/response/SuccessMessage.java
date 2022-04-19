package edu.eskisehir.teklifyap.model.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class SuccessMessage {

    private final LocalDateTime timeStamp = LocalDateTime.now();
    private final String message;
    private final String path;
    private final String account;
    private final String status = HttpStatus.OK.toString();
}
