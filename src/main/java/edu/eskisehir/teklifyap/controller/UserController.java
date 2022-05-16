package edu.eskisehir.teklifyap.controller;

import edu.eskisehir.teklifyap.config.security.PasswordEncoder;
import edu.eskisehir.teklifyap.model.User;
import edu.eskisehir.teklifyap.model.request.UpdateUserRequest;
import edu.eskisehir.teklifyap.model.response.ShortMaterialResponse;
import edu.eskisehir.teklifyap.model.response.ShortUserResponse;
import edu.eskisehir.teklifyap.model.response.SuccessMessage;
import edu.eskisehir.teklifyap.service.MaterialService;
import edu.eskisehir.teklifyap.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final MaterialService materialService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<?> getUserProfile(@RequestParam("user") int uid) throws Exception {
        User user = userService.findById(uid);
        ShortUserResponse response = new ShortUserResponse(user);
        return ResponseEntity.ok(response);
    }

    @PutMapping()
    public ResponseEntity<?> save(HttpServletRequest request, @RequestParam("user") int uid, @RequestBody UpdateUserRequest user)
            throws Exception {

        User updated = userService.findById(uid);

        if (user.getPassword() != null) {
            updated.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword()));
        }
        if (user.getEmail() != null) {
            updated.setMail(user.getEmail());
        }
        if (user.getName() != null) {
            updated.setName(user.getName());
        }
        if (user.getSurname() != null) {
            updated.setSurname(user.getSurname());
        }

        userService.save(updated);

        return ResponseEntity.ok(new SuccessMessage("saved", request.getServletPath(), ""));
    }

    @PostMapping("/assignList")
    public ResponseEntity<?> assignList(@RequestBody User user) {
        return ResponseEntity.ok(userService.getUserProfile(user));
    }

    @GetMapping("/getFullName")
    public ResponseEntity<?> getFullName(HttpServletRequest request, @RequestParam("user") int uid) {
        User a = userService.getById(uid);
        return ResponseEntity.ok(new SuccessMessage("done", request.getServletPath(), a.FullName()));
    }

    @GetMapping("/getMaterials")
    public ResponseEntity<List<ShortMaterialResponse>> getMaterials(HttpServletRequest request, @RequestParam("user") int uid) {
        List<ShortMaterialResponse> materialList = materialService.findByUserId(uid);
        return ResponseEntity.ok(materialList);
    }

}
