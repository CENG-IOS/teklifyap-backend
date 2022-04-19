package edu.eskisehir.teklifyap.controller;

import edu.eskisehir.teklifyap.model.Material;
import edu.eskisehir.teklifyap.model.Offer;
import edu.eskisehir.teklifyap.model.response.SuccessMessage;
import edu.eskisehir.teklifyap.service.OfferService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/offer")
@CrossOrigin
@AllArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @GetMapping
    public ResponseEntity<Offer> getOne(HttpServletRequest request, @RequestParam("offer") int fid) throws Exception {

        return ResponseEntity.ok(offerService.findById(fid));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(offerService.getAll());
    }

    @GetMapping("/getOffers")
    public ResponseEntity<?> getOffersByUser(@RequestBody String id) throws ParseException {
        return ResponseEntity.ok(offerService.getOffersByUser(id));
    }

    @PutMapping
    public ResponseEntity<SuccessMessage> updateStatus(@RequestParam("id") int id, @RequestParam("status") String status) {
        offerService.updateStatus(id, status);
        return ResponseEntity.ok(new SuccessMessage("updated","",""));
    }

    @PostMapping("/make")
    public ResponseEntity<?> makeOffer(@RequestBody Offer offer) {
        offerService.makeOffer(offer);
        return ResponseEntity.ok(new SuccessMessage("made","",""));
    }
}
