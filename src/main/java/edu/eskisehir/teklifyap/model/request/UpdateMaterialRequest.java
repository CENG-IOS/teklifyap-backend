package edu.eskisehir.teklifyap.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMaterialRequest {

    private String name;
    private String unit;
    private Double pricePerUnit;
}
