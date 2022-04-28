package edu.eskisehir.teklifyap.model.request;

import edu.eskisehir.teklifyap.model.MaterialWithPrice;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MakingOfferRequest {

    private String userName;
    private String title;
    private String toWho;
    private LocalDate date;
    private int profitRate;
    private double totalPrice;
    private int day;
    private List<MaterialWithPrice> materials;
}
