package edu.eskisehir.teklifyap.model;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "offer_material")
public class OfferMaterial {

    public OfferMaterial(Material material, Offer offer, double unitQuantity, double pricePerUnit) {
        this.material = material;
        this.offer = offer;
        this.unitQuantity = unitQuantity;
        this.pricePerUnit = pricePerUnit;
    }

    public OfferMaterial(int id, double unitQuantity) {
        this.id = id;
        this.unitQuantity = unitQuantity;
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

    @Column(name = "unit_quantity")
    private double unitQuantity;

    @Column(name = "price_per_unit")
    private double pricePerUnit;
}
