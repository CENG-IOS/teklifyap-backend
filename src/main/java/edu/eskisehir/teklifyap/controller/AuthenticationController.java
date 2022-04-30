package edu.eskisehir.teklifyap.controller;

import edu.eskisehir.teklifyap.core.Singleton;
import edu.eskisehir.teklifyap.model.ConfirmationToken;
import edu.eskisehir.teklifyap.model.Material;
import edu.eskisehir.teklifyap.model.User;
import edu.eskisehir.teklifyap.model.request.LoginRequest;
import edu.eskisehir.teklifyap.model.request.RegisterRequest;
import edu.eskisehir.teklifyap.model.response.AuthenticationResponse;
import edu.eskisehir.teklifyap.model.response.SuccessMessage;
import edu.eskisehir.teklifyap.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@AllArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final BlacklistService blackListService;
    private final ConfirmationTokenService confirmationTokenService;
    private final MaterialService materialService;
    private final OfferService offerService;
    private final OfferMaterialService offerMaterialService;

    @PostMapping
    public ResponseEntity<SuccessMessage> register(HttpServletRequest request, @RequestBody RegisterRequest body) throws Exception {

        System.out.println(body);

        if (body.getName() == null || body.getMail() == null || body.getPassword() == null || body.getPassword().length() < 6)
            throw new Exception("InvalidParametersException");
        if (!Singleton.validate(body.getMail()))
            throw new Exception("InvalidMailException");
        User user = userService.create(new User(body.getName(), body.getSurname(), body.getMail(), body.getPassword()));
        authenticationService.sendRegistrationMail(body);
        log.info("User registration...");

        List<Material> materials = new LinkedList<>();
        List<String> allLines = Files.readAllLines(Paths.get("default_materials.txt"));
        for (String line : allLines) {
            String[] arr = line.split("-");
            Material material = new Material(user, arr[0], arr[1], false, 0);
            materials.add(material);
        }

        Material sgk = new Material(user, "SGK Stopaj Bedeli", "-", true, 1);
        materials.add(sgk);

        materialService.saveAll(materials);

        return ResponseEntity.ok(new SuccessMessage("registered", request.getServletPath(), body.getMail()));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(HttpServletRequest request, @RequestBody LoginRequest loginRequest)
            throws Exception {

        User user = userService.findByMail(loginRequest.getMail());
        String token = authenticationService.authenticate(loginRequest);
        return ResponseEntity.ok(new AuthenticationResponse("200", token,user.getId(), request.getServletPath()));
    }

    @DeleteMapping
    public ResponseEntity<SuccessMessage> logout(HttpServletRequest request) throws Exception {

        final String token = request.getHeader("Authorization").substring(7);
        log.info("Blacklisting -> " + token);
        blackListService.insertJWTtoBlacklist(token);
        log.info("User logged out!");
        return ResponseEntity.ok(new SuccessMessage("BlackListingComplete", request.getServletPath(), token));
    }

    @GetMapping("/verifyUser")
    public ResponseEntity<SuccessMessage> verifyUser(HttpServletRequest request, @RequestParam("token") String token,
                                                     @RequestParam("mail") String mail) throws Exception {

        Optional<ConfirmationToken> validate = confirmationTokenService.validate(token, mail);

        if (validate.isPresent()) {

            User user = userService.findByMail(mail);
            user.setConfirmed(true);
            userService.save(user);

            confirmationTokenService.delete(validate.get());

            return ResponseEntity.ok(new SuccessMessage("confirmed", request.getServletPath(), ""));
        } else throw new Exception("Error");

    }


}
