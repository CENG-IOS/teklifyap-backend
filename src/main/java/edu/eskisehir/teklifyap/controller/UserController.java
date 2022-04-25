package edu.eskisehir.teklifyap.controller;

import edu.eskisehir.teklifyap.model.User;
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
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final MaterialService materialService;

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
        return ResponseEntity.ok(new SuccessMessage("saved", "", ""));
    }

    @PostMapping("/assignList")
    public ResponseEntity<?> assignList(@RequestBody User user) {
        return ResponseEntity.ok(userService.getUserProfile(user));
    }

    @GetMapping("/getAll/{id}")
    public ResponseEntity<User> getByUserID(@PathVariable int id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping("/getFullName")
    public ResponseEntity<String> getFullName(@RequestBody User user) {
        User a = userService.getById(user.getId());
        return ResponseEntity.ok(a.FullName());
    }

    @GetMapping("/getMaterials")
    public ResponseEntity<List<ShortMaterialResponse>> getMaterials(HttpServletRequest request, @RequestParam("user") int uid) {
        List<ShortMaterialResponse> materialList = materialService.findByUserId(uid);
        return ResponseEntity.ok(materialList);
    }

}
