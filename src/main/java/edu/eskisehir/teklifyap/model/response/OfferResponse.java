package edu.eskisehir.teklifyap.model.response;

import edu.eskisehir.teklifyap.model.OfferMaterial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferResponse {

    private int id;
    private String title;
    private boolean status;
    private double profitRate;
    private LocalDate date;
    private double price;
    private double kdv;
    private double sgk;
    private double totalPrice;
    private List<OfferMaterial> materials;
}
