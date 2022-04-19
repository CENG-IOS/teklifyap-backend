package edu.eskisehir.teklifyap.controller;

import edu.eskisehir.teklifyap.model.OfferMaterial;
import edu.eskisehir.teklifyap.model.response.SuccessMessage;
import edu.eskisehir.teklifyap.service.OfferMaterialService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/api/offerMaterial")
@CrossOrigin
@AllArgsConstructor
public class OfferMaterialController {
    private final OfferMaterialService offerMaterialService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(offerMaterialService.getAll());
    }

    @PostMapping
    public ResponseEntity<?> makeOffer(@RequestBody OfferMaterial offerMaterial) {
        offerMaterialService.makeOffer(offerMaterial);
        return ResponseEntity.ok(new SuccessMessage("made", "", ""));
    }

    @PostMapping("/makes")
    public ResponseEntity<?> makeOffers(@RequestBody List<OfferMaterial> offerMaterials) {
        offerMaterialService.makeOffers(offerMaterials);
        return ResponseEntity.ok(new SuccessMessage("made", "", ""));
    }

    @GetMapping("/getMaterialsByOffer")
    public ResponseEntity<?> getMaterialsByOffer(@RequestBody LinkedHashMap<String, Integer> id) {
        return ResponseEntity.ok(offerMaterialService.getMaterialsByOffer(id.get("id")));
    }

}
