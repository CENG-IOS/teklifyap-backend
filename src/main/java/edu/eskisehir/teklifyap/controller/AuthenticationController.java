package edu.eskisehir.teklifyap.controller;

import edu.eskisehir.teklifyap.core.Singleton;
import edu.eskisehir.teklifyap.model.User;
import edu.eskisehir.teklifyap.model.request.RegisterRequest;
import edu.eskisehir.teklifyap.model.response.AuthenticationResponse;
import edu.eskisehir.teklifyap.model.response.SuccessMessage;
import edu.eskisehir.teklifyap.service.AuthenticationService;
import edu.eskisehir.teklifyap.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@AllArgsConstructor
@Slf4j

public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<SuccessMessage> register(HttpServletRequest request, @RequestBody RegisterRequest body) throws Exception {

        if (body.getName() == null || body.getMail() == null || body.getPassword() == null || body.getPassword().length() < 8)
            throw new Exception("InvalidParametersException");
        if (!Singleton.validate(body.getMail()))
            throw new Exception("InvalidMailException");
        userService.create(new User(body.getName(), body.getSurname(), body.getMail(), body.getPassword()));
//        authenticationService.sendToken(body);
        log.info("User registration...");

        return ResponseEntity.ok(new SuccessMessage("registered", request.getServletPath(), body.getMail()));
    }

}
