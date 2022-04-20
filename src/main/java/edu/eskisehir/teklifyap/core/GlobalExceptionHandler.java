package edu.eskisehir.teklifyap.core;

import edu.eskisehir.teklifyap.model.response.ExceptionDetailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({AuthenticationException.class, UnauthorizedException.class})
    public ResponseEntity<?> handleAuthenticationException(Exception exception, WebRequest request) {

        ExceptionDetailResponse details = new ExceptionDetailResponse(HttpStatus.UNAUTHORIZED.value(),
                exception.getMessage().split("Exception")[0], request.getDescription(false).split("uri=")[1]);
        return new ResponseEntity<>(details, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception, WebRequest request) {

        ExceptionDetailResponse details = new ExceptionDetailResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage().split("Exception")[0], request.getDescription(false).split("uri=")[1]);
        return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
