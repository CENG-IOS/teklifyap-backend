package edu.eskisehir.teklifyap.controller;

import edu.eskisehir.teklifyap.model.Material;
import edu.eskisehir.teklifyap.model.response.ShortMaterialResponse;
import edu.eskisehir.teklifyap.model.response.SuccessMessage;
import edu.eskisehir.teklifyap.model.User;
import edu.eskisehir.teklifyap.service.MaterialService;
import edu.eskisehir.teklifyap.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/material")
@CrossOrigin
@AllArgsConstructor
public class MaterialController {

    private final MaterialService materialService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Material> getOne(HttpServletRequest request, @RequestParam("material") int mid) throws Exception {

        return ResponseEntity.ok(materialService.findById(mid));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ShortMaterialResponse>> getAll() {
        return ResponseEntity.ok(materialService.getAll());
    }

    @GetMapping("/getMaterialByUser")
    public List<ShortMaterialResponse> getMaterials(@RequestParam("user") int uid) {
        return materialService.getMaterials(uid);
    }

    @PostMapping
    public ResponseEntity<?> add(HttpServletRequest request, @RequestBody Material material, @RequestParam("user") int uid) throws Exception {
        User user = userService.findById(uid);
        material.setUser(user);
        materialService.save(material);
        return ResponseEntity.ok(new SuccessMessage("added", request.getServletPath(), ""));
    }

   @DeleteMapping
    public ResponseEntity<?> delete(HttpServletRequest request, @RequestParam("material") String mid, @RequestParam("user") int uid) throws Exception {
        materialService.delete(mid);
       return ResponseEntity.ok(new SuccessMessage("deleted", request.getServletPath(), ""));
    }
    @PutMapping
    public ResponseEntity<?> update(HttpServletRequest request, @RequestBody Material material,@RequestParam("user") int uid) throws Exception {
        User user = userService.findById(uid);
        material.setUser(user);
        materialService.update(material);
        return ResponseEntity.ok(new SuccessMessage("updated", request.getServletPath(), ""));
    }

}
