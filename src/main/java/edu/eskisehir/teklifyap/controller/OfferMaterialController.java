package edu.eskisehir.teklifyap.controller;

import edu.eskisehir.teklifyap.model.request.MakingOfferRequest;
import edu.eskisehir.teklifyap.model.response.SuccessMessage;
import edu.eskisehir.teklifyap.service.OfferMaterialService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

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
    public ResponseEntity<?> makeOffer(HttpServletRequest request, @RequestBody MakingOfferRequest makingOfferRequest,
                                       @RequestParam("user") int uid) throws Exception {

        offerMaterialService.makeOffer(makingOfferRequest, uid);
        return ResponseEntity.ok(new SuccessMessage("done", request.getServletPath(), ""));
    }

    @GetMapping("/getMaterialsByOffer")
    public ResponseEntity<?> getMaterialsByOffer(@RequestBody LinkedHashMap<String, Integer> id) {
        return ResponseEntity.ok(offerMaterialService.getMaterialsByOffer(id.get("id")));
    }

}
