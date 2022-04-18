package edu.eskisehir.teklifyap.controller;

import edu.eskisehir.teklifyap.model.SuccessMessage;
import edu.eskisehir.teklifyap.model.User;
import edu.eskisehir.teklifyap.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

//    @PostMapping("/getByEmailAndPassword")
//    public ResponseEntity<?> getByEmailAndPassword(@RequestBody User user) {
//        return ResponseEntity.ok(userService.getByEmailAndPassword(user));
//    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody User user) {
        return ResponseEntity.ok(userService.add(user));
    }

//    @PostMapping("/userInfo")
//    public ResponseEntity<List<User>> userInfo(@RequestBody int userInfo) {
//        return ResponseEntity.ok(userService.userInfo(userInfo));
//    }

    @PostMapping("/userProfile")
    public ResponseEntity<?> getUserProfile(@RequestBody User user) {
        return ResponseEntity.ok(userService.getUserProfile(user));
    }

    @PostMapping("/updateInformation")
    public ResponseEntity<?> save(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(new SuccessMessage("saved","",""));
    }

    @PostMapping("/assignList")
    public ResponseEntity<?> assignList(@RequestBody User user) {
        return ResponseEntity.ok(userService.getUserProfile(user));
    }

    @GetMapping("/getAll/{id}")
    public ResponseEntity<User> getByUserID(@PathVariable int id) {
        return ResponseEntity.ok(userService.getByid(id));
    }

    @PostMapping("/getFullName")
    public ResponseEntity<String> getFullName(@RequestBody User user) {
        User a = userService.getByid(user.getUser_id());
        return ResponseEntity.ok(a.FullName());
    }
}
