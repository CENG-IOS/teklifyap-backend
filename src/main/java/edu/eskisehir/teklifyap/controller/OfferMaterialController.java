package edu.eskisehir.teklifyap.controller;

import edu.eskisehir.teklifyap.model.Material;
import edu.eskisehir.teklifyap.model.Offer;
import edu.eskisehir.teklifyap.model.OfferMaterial;
import edu.eskisehir.teklifyap.model.request.AddingOfferMaterialRequest;
import edu.eskisehir.teklifyap.model.response.OfferResponse;
import edu.eskisehir.teklifyap.model.response.SuccessMessage;
import edu.eskisehir.teklifyap.service.MaterialService;
import edu.eskisehir.teklifyap.service.OfferMaterialService;
import edu.eskisehir.teklifyap.service.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/offerMaterial")
@CrossOrigin
@AllArgsConstructor
public class OfferMaterialController {

    private final OfferMaterialService offerMaterialService;
    private final OfferService offerService;
    private final MaterialService materialService;

    //teklifin detaylarını görüntülemeye çalışırken kullanılacak. Tekliflerim sayfasında inceleye bastığında çalışır.
    @GetMapping()
    public ResponseEntity<?> getMaterialsByOffer(@RequestParam("offer") int oid) throws Exception {

        Offer offer = offerService.findById(oid);
        List<OfferMaterial> offerMaterials = offerMaterialService.findByOffer(offer);

        for (int i = 0; i < offerMaterials.size(); i++) {
            offerMaterials.get(i).setOffer(null);
        }

        OfferResponse offerResponse = new OfferResponse();
        offerResponse.setId(offer.getId());
        offerResponse.setTitle(offer.getTitle());
        offerResponse.setDate(offer.getDate());
        offerResponse.setMaterials(offerMaterials);

        return ResponseEntity.ok(offerResponse);
    }

    @DeleteMapping
    public ResponseEntity<SuccessMessage> deleteOneMaterialInOffer(HttpServletRequest request, @RequestParam("offerMaterial") int omid)
            throws Exception {

        offerMaterialService.delete(offerMaterialService.findById(omid));

        return ResponseEntity.ok(new SuccessMessage("deleted", request.getServletPath(), ""));
    }

    @PostMapping
    public ResponseEntity<?> addMaterialToOffer(HttpServletRequest request, @RequestBody AddingOfferMaterialRequest request1)
            throws Exception {

        Offer offer = offerService.findById(request1.getOid());
        Material material = materialService.findById(request1.getMid());
        OfferMaterial offerMaterial = new OfferMaterial(material, offer, request1.getUnitQuantity(), request1.getPricePerUnit());
        offerMaterialService.save(offerMaterial);

        return ResponseEntity.ok(new SuccessMessage("added", request.getServletPath(), ""));
    }

}
