package edu.eskisehir.teklifyap.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialTableRow {

    private int no;
    private String name;
    private String unit;
    private String unitPrice;
    private String pricePerUnit;
    private double profitRate;
    private String total;
}
