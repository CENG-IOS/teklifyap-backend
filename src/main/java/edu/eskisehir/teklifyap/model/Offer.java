package edu.eskisehir.teklifyap.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "offer")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Offer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "material", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OfferMaterial> materialList;

    @Column(name = "offer_title")
    private String title;

    @Column(name = "offer_company_name")
    private String companyName;

    @Column(name = "offer_status")
    private OfferStatus status;

    @Column(name = "offer_total_price")
    private double totalPrice;

    @Column(name = "offer_date")
    private LocalDate date;

    @Column(name = "offer_profit_rate")
    private int profitRate;

    @Column(name = "offer_username")
    private String username;

    @Column(name = "offer_kdv_price")
    private double kdv;

    public enum OfferStatus {
        PENDING, POSITIVE, NEGATIVE
    }
}
