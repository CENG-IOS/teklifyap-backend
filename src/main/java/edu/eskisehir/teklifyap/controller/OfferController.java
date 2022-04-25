package edu.eskisehir.teklifyap.controller;

import edu.eskisehir.teklifyap.model.Offer;
import edu.eskisehir.teklifyap.model.request.MakingOfferRequest;
import edu.eskisehir.teklifyap.model.request.UpdateOfferRequest;
import edu.eskisehir.teklifyap.model.response.SuccessMessage;
import edu.eskisehir.teklifyap.service.OfferMaterialService;
import edu.eskisehir.teklifyap.service.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/offer")
@CrossOrigin
@AllArgsConstructor
public class OfferController {

    private final OfferService offerService;
    private final OfferMaterialService offerMaterialService;

    @GetMapping
    public ResponseEntity<Offer> getOne(@RequestParam("offer") int fid) throws Exception {

        return ResponseEntity.ok(offerService.findById(fid));
    }

    @PostMapping
    public ResponseEntity<?> makeOffer(HttpServletRequest request, @RequestBody MakingOfferRequest makingOfferRequest,
                                       @RequestParam("user") int uid) throws Exception {

        offerMaterialService.makeOffer(makingOfferRequest, uid);
        return ResponseEntity.ok(new SuccessMessage("done", request.getServletPath(), ""));
    }

    @GetMapping("/getOffers")
    public ResponseEntity<?> getOffersByUser(HttpServletRequest request, @RequestParam("user") int uid,
                                             @RequestParam(defaultValue = "0", value = "page") int offset,
                                             @RequestParam(defaultValue = "asc", value = "sort") String sorting,
                                             @RequestParam(defaultValue = "date", value = "sortBy") String sortBy)
            throws Exception {

        int pageSize = 15;
        if (offset < 0) {
            offset = 0;
        }

        if (!sorting.equalsIgnoreCase("asc") && !sorting.equalsIgnoreCase("desc"))
            throw new Exception("UnknownSortingParameterException");
        if (!sortBy.equalsIgnoreCase("name") && !sortBy.equalsIgnoreCase("date"))
            throw new Exception("WrongFieldException");

        Sort sort;
        if (sortBy.equalsIgnoreCase("name")) {
            if (sorting.equalsIgnoreCase("asc"))
                sort = Sort.by("name").ascending();
            else
                sort = Sort.by("name").descending();
        } else {
            if (sorting.equalsIgnoreCase("asc"))
                sort = Sort.by("offer_date").ascending();
            else
                sort = Sort.by("offer_date").descending();
        }

        return ResponseEntity.ok(offerService.getOffersByUser(uid, PageRequest.of(offset, pageSize, sort)));
    }

    @PutMapping
    public ResponseEntity<SuccessMessage> updateOffer(HttpServletRequest request, @RequestParam("offer") int fid,
                                                      @RequestBody UpdateOfferRequest update) throws Exception {

        Offer offer = offerService.findById(fid);

        if (update.getCompanyName() != null) {
            offer.setCompanyName(update.getCompanyName());
        }
        if (update.getStatus() != null) {
            offer.setStatus(update.getStatus());
        }
        if (update.getTotalPrice() != null) {
            offer.setTotalPrice(update.getTotalPrice());
        }
        if (update.getDate() != null) {
            offer.setDate(update.getDate());
        }
        if (update.getProfitRate() != null) {
            offer.setProfitRate(update.getProfitRate());
        }
        if (update.getUsername() != null) {
            offer.setUsername(update.getUsername());
        }
        if (update.getKdv() != null) {
            offer.setKdv(update.getKdv());
        }

        offerService.save(offer);

        return ResponseEntity.ok(new SuccessMessage("updated", request.getServletPath(), ""));
    }
}
