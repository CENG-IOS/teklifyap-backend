package edu.eskisehir.teklifyap.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShortOfferMaterial {

    private int id;
    private int offer_id;
    private Material material;
    private double unitPrice;
}
