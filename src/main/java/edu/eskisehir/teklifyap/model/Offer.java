package edu.eskisehir.teklifyap.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "offer")
public class Offer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "offer_title")
    private String title;

    @Column(name = "offer_company_name")
    private String companyName;

    @Column(name = "offer_status")
    private String status;

    @Column(name = "offer_total_price")
    private int totalPrice;

    @Column(name = "offer_date")
    private Date date;

    @Column(name = "offer_profit_rate")
    private int profitRate;

    @Column(name = "offer_username")
    private String username;

    @Column(name = "offer_kdv_price")
    private double kdv;
}
