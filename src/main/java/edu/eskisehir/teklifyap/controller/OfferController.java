package edu.eskisehir.teklifyap.controller;

import edu.eskisehir.teklifyap.model.Offer;
import edu.eskisehir.teklifyap.model.OfferMaterial;
import edu.eskisehir.teklifyap.model.ShortOfferMaterial;
import edu.eskisehir.teklifyap.model.request.MakingOfferRequest;
import edu.eskisehir.teklifyap.model.request.UpdateOfferRequest;
import edu.eskisehir.teklifyap.model.response.SuccessMessage;
import edu.eskisehir.teklifyap.service.OfferMaterialService;
import edu.eskisehir.teklifyap.service.OfferService;
import edu.eskisehir.teklifyap.service.PdfGenerateService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/offer")
@CrossOrigin
@AllArgsConstructor
public class OfferController {

    private final OfferService offerService;
    private final OfferMaterialService offerMaterialService;
    private final PdfGenerateService pdfGenerateService;

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

    @DeleteMapping("/delete")
    public ResponseEntity<SuccessMessage> deleteOffer(HttpServletRequest request, @RequestParam("offer") int fid) {
        offerService.delete(fid);
        return ResponseEntity.ok(new SuccessMessage("deleted", request.getServletPath(), ""));
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

        offerService.save(offer);

        return ResponseEntity.ok(new SuccessMessage("updated", request.getServletPath(), ""));
    }

    @PutMapping("/changeStatus")
    public ResponseEntity<SuccessMessage> changeStatus(HttpServletRequest request, @RequestParam("offer") int fid) throws Exception {
        Offer offer = offerService.findById(fid);
        offer.setStatus(!offer.isStatus());
        offerService.save(offer);
        return ResponseEntity.ok(new SuccessMessage("changed", request.getServletPath(), ""));
    }

    @GetMapping("/download")
    public ResponseEntity<?> downloadPDF(@RequestParam("offer") int oid) throws Exception {

        Offer offer = offerService.findById(oid);
        List<OfferMaterial> materialList = offerMaterialService.findByOffer(offer);
        List<ShortOfferMaterial> shortMaterialList = new ArrayList<>();

        for (OfferMaterial offerMaterial : materialList) {
            ShortOfferMaterial shortOfferMaterial = new ShortOfferMaterial(offerMaterial);
            shortMaterialList.add(shortOfferMaterial);
        }

        byte[] bytes = pdfGenerateService.generateOfferPdf(offer, shortMaterialList);


        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }

}
