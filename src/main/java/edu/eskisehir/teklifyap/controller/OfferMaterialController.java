package edu.eskisehir.teklifyap.controller;

import edu.eskisehir.teklifyap.model.request.MakingOfferRequest;
import edu.eskisehir.teklifyap.model.response.SuccessMessage;
import edu.eskisehir.teklifyap.service.OfferMaterialService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/offerMaterial")
@CrossOrigin
@AllArgsConstructor
public class OfferMaterialController {

    private final OfferMaterialService offerMaterialService;

    @PostMapping
    public ResponseEntity<?> makeOffer(HttpServletRequest request, @RequestBody MakingOfferRequest makingOfferRequest,
                                       @RequestParam("user") int uid) throws Exception {

        offerMaterialService.makeOffer(makingOfferRequest, uid);
        return ResponseEntity.ok(new SuccessMessage("done", request.getServletPath(), ""));
    }

    //teklifin detaylarını görüntülemeye çalışırken kullanılacak. Tekliflerim sayfasında inceleye bastığında çalışır.
    @GetMapping("/getMaterialsByOffer")
    public ResponseEntity<?> getMaterialsByOffer(@RequestParam("offer") int oid) {

        return ResponseEntity.ok(offerMaterialService.getMaterialsByOffer(oid));
    }

}
