package edu.eskisehir.teklifyap.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ShortOfferMaterial {

    private int id;
    private int offer_id;
    private String title;
    private LocalDate date;
    private Material material;
    private double unitQuantity;

    public ShortOfferMaterial(OfferMaterial offerMaterial) {
        this.id = offerMaterial.getId();
        this.offer_id = offerMaterial.getOffer().getId();
        this.title = offerMaterial.getOffer().getTitle();
        this.date = offerMaterial.getOffer().getDate();
        this.material = offerMaterial.getMaterial();
        this.unitQuantity = offerMaterial.getUnitQuantity();
    }
}
