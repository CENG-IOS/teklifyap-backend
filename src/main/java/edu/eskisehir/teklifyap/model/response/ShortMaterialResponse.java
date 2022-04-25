package edu.eskisehir.teklifyap.model.response;

import edu.eskisehir.teklifyap.model.Material;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortMaterialResponse {

    public ShortMaterialResponse(Material material) {
        this.id = material.getId();
        this.name = material.getName();
        this.unit = material.getUnit();
        this.deleted = material.isDeleted();
        this.fixed = material.isFixed();
        this.pricePerUnit = material.getPricePerUnit();
    }

    private int id;
    private String name;
    private String unit;
    private boolean deleted;
    private boolean fixed;
    private double pricePerUnit;
}
