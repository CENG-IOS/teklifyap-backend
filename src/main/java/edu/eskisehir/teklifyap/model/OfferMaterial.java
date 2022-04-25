package edu.eskisehir.teklifyap.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "offer_material")
public class OfferMaterial {

    public OfferMaterial(Material material, Offer offer, double unitPrice) {
        this.material = material;
        this.offer = offer;
        this.unitPrice = unitPrice;
    }

    public OfferMaterial(int id, double unitPrice) {
        this.id = id;
        this.unitPrice = unitPrice;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @Column(name = "unit_price")
    private double unitPrice;
}
