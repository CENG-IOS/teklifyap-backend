package edu.eskisehir.teklifyap.service;

import edu.eskisehir.teklifyap.config.security.JwtTokenUtil;
import edu.eskisehir.teklifyap.core.Singleton;
import edu.eskisehir.teklifyap.model.ConfirmationToken;
import edu.eskisehir.teklifyap.model.request.LoginRequest;
import edu.eskisehir.teklifyap.model.request.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final ConfirmationTokenService confirmationTokenService;
    private final MailService mailService;

    public String authenticate(LoginRequest request) throws Exception {

        log.info("authenticate(" + request.getMail() + ") -> ...");

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getMail(), request.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("DisabledException");
        } catch (BadCredentialsException e) {
            throw new Exception("BadCredentialsException");
        }

        return jwtTokenUtil.generateToken(userService.findByMail(request.getMail()), request.isRememberMe());
    }

    public void sendRegistrationMail(RegisterRequest body) throws MessagingException {

        String token = Singleton.generateRandomString(20);
        confirmationTokenService.save(new ConfirmationToken(token, body.getMail()));

        String link = "http://localhost:8080/auth/verifyUser?token=" + token + "&mail=" + body.getMail();
        System.out.println(link);

        Map<String, String> content = new HashMap<>();
        content.put("name", body.getName());
        content.put("link", link);
        mailService.sendMail(body.getMail(), "Teklif Yap - Hesap Onayı", "ConfirmationMail", content);
    }
}
