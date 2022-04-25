package edu.eskisehir.teklifyap.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ShortOffer {

    private int id;
    private String title;
    private LocalDate date;

    public ShortOffer(Offer offer){
        this.id = offer.getId();
        this.title = offer.getTitle();
        this.date = offer.getDate();
    }
}
