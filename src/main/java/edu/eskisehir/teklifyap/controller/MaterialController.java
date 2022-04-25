package edu.eskisehir.teklifyap.controller;

import edu.eskisehir.teklifyap.model.Material;
import edu.eskisehir.teklifyap.model.User;
import edu.eskisehir.teklifyap.model.request.AddingMaterialRequest;
import edu.eskisehir.teklifyap.model.response.ShortMaterialResponse;
import edu.eskisehir.teklifyap.model.response.SuccessMessage;
import edu.eskisehir.teklifyap.service.MaterialService;
import edu.eskisehir.teklifyap.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @GetMapping("/getMaterialByUser")
    public List<ShortMaterialResponse> getMaterials(@RequestParam("user") int uid) {
        return materialService.getMaterials(uid);
    }

    @PostMapping
    public ResponseEntity<SuccessMessage> addMaterialToUser(HttpServletRequest request, @RequestParam("user") int uid,
                                                            @RequestBody AddingMaterialRequest material) throws Exception {

        Material created = new Material(material);
        User user = userService.findById(uid);

        created.setUser(user);
        materialService.save(created);

        return ResponseEntity.ok(new SuccessMessage("added", request.getServletPath(), ""));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(HttpServletRequest request, @RequestParam("material") String mid, @RequestParam("user") int uid)
            throws Exception {
        materialService.delete(mid);
        return ResponseEntity.ok(new SuccessMessage("deleted", request.getServletPath(), ""));
    }

    @PutMapping
    public ResponseEntity<?> update(HttpServletRequest request, @RequestBody Material material, @RequestParam("user") int uid)
            throws Exception {
        User user = userService.findById(uid);
        material.setUser(user);
        materialService.update(material);
        return ResponseEntity.ok(new SuccessMessage("updated", request.getServletPath(), ""));
    }

}
