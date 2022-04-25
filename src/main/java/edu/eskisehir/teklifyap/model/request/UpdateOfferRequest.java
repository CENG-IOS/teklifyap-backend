package edu.eskisehir.teklifyap.model.request;

import edu.eskisehir.teklifyap.model.Offer;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateOfferRequest {

    private String title;
    private String companyName;
    private Offer.OfferStatus status;
    private Double totalPrice;
    private LocalDate date;
    private Integer profitRate;
    private String username;
    private Double kdv;
}
