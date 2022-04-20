package edu.eskisehir.teklifyap.controller;

import edu.eskisehir.teklifyap.core.Singleton;
import edu.eskisehir.teklifyap.model.User;
import edu.eskisehir.teklifyap.model.request.LoginRequest;
import edu.eskisehir.teklifyap.model.request.RegisterRequest;
import edu.eskisehir.teklifyap.model.response.AuthenticationResponse;
import edu.eskisehir.teklifyap.model.response.SuccessMessage;
import edu.eskisehir.teklifyap.service.AuthenticationService;
import edu.eskisehir.teklifyap.service.BlacklistService;
import edu.eskisehir.teklifyap.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@AllArgsConstructor
@Slf4j

public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final BlacklistService blackListService;

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

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(HttpServletRequest request, @RequestBody LoginRequest loginRequest)
            throws Exception {
        String token = authenticationService.authenticate(loginRequest);
        return ResponseEntity.ok(new AuthenticationResponse("200", token, request.getServletPath()));
    }

    @DeleteMapping
    public ResponseEntity<SuccessMessage> logout(HttpServletRequest request) throws Exception {

        final String token = request.getHeader("Authorization").substring(7);
        log.info("Blacklisting -> " + token);
        blackListService.insertJWTtoBlacklist(token);
        log.info("User logged out!");
        return ResponseEntity.ok(new SuccessMessage("BlackListingComplete", request.getServletPath(), token));
    }


}
