package edu.eskisehir.teklifyap.controller;

import edu.eskisehir.teklifyap.model.Material;
import edu.eskisehir.teklifyap.model.response.ShortMaterialResponse;
import edu.eskisehir.teklifyap.model.response.SuccessMessage;
import edu.eskisehir.teklifyap.model.User;
import edu.eskisehir.teklifyap.service.MaterialService;
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
    public ResponseEntity<?> add(HttpServletRequest request, @RequestBody Material material) {
        materialService.save(material);
        return ResponseEntity.ok(new SuccessMessage("added", request.getServletPath(), ""));
    }

    @PostMapping("/adds")
    public ResponseEntity<?> adds(HttpServletRequest request, @RequestBody List<Material> materials) {
        materialService.adds(materials);
        return ResponseEntity.ok(new SuccessMessage("added", request.getServletPath(), ""));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(HttpServletRequest request, @RequestBody String deleted) throws Exception {
        materialService.delete(deleted);
        return ResponseEntity.ok(new SuccessMessage("deleted", request.getServletPath(), ""));
    }

    @PutMapping
    public ResponseEntity<?> update(HttpServletRequest request, @RequestBody Material material) {
        materialService.update(material);
        return ResponseEntity.ok(new SuccessMessage("updated", request.getServletPath(), ""));

    }

}
