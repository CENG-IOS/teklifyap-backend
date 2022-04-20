package edu.eskisehir.teklifyap.model.request;

import lombok.Data;

@Data
public class AddingMaterialRequest {

    private String name;
    private String unit;
    private int pricePerUnit;
}
