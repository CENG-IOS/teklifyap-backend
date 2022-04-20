package edu.eskisehir.teklifyap.service;

import edu.eskisehir.teklifyap.config.security.JwtTokenUtil;
import edu.eskisehir.teklifyap.model.User;
import edu.eskisehir.teklifyap.model.request.LoginRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    public String authenticate(LoginRequest request) throws Exception {

        log.info("authenticate(" + request.getMail() + ") -> ...");

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getMail(), request.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("DisabledException");
        } catch (BadCredentialsException e) {
            throw new Exception("BadCredentialsException");
        }
        if (userService.findByMail(request.getMail()).isPresent())
            return jwtTokenUtil.generateToken(userService.findByMail(request.getMail()).get(), request.isRememberMe());
        else throw new Exception("UserNotFoundException");
    }
}
