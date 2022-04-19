package edu.eskisehir.teklifyap.controller;

import edu.eskisehir.teklifyap.model.Offer;
import edu.eskisehir.teklifyap.model.response.SuccessMessage;
import edu.eskisehir.teklifyap.service.OfferService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/offer")
@CrossOrigin
@AllArgsConstructor
public class OfferController {
    private final OfferService offerService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(offerService.getAll());
    }

    @PostMapping("/getOffers")
    public ResponseEntity<?> getOffersByUser(@RequestBody String id) throws ParseException {
        return ResponseEntity.ok(offerService.getOffersByUser(id));
    }

    @PostMapping("/updateStatus/{id}/{status}")
    public ResponseEntity<SuccessMessage> updateStatus(@PathVariable int id, @PathVariable String status) {
        offerService.updateStatus(id, status);
        return ResponseEntity.ok(new SuccessMessage("updated","",""));
    }

    @PostMapping("/make")
    public ResponseEntity<?> makeOffer(@RequestBody Offer offer) {
        offerService.makeOffer(offer);
        return ResponseEntity.ok(new SuccessMessage("made","",""));
    }


}
