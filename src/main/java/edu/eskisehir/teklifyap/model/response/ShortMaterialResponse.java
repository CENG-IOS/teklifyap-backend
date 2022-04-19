package edu.eskisehir.teklifyap.model.response;

import edu.eskisehir.teklifyap.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortMaterialResponse {

    private int id;
    private String name;
    private String unit;
    private boolean deleted;
    private boolean fixed;
    private int pricePerUnit;
}
