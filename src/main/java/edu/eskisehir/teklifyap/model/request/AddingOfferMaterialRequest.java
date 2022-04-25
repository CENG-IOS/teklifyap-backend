package edu.eskisehir.teklifyap.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddingOfferMaterialRequest {

    private int oid;
    private int mid;
    private double unitPrice;
}
