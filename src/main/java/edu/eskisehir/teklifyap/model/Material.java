package edu.eskisehir.teklifyap.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "material")
public class Material implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    private int id;

    public Material(int id, String name, String unit, boolean deleted, boolean fixed, int pricePerUnit) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.deleted = deleted;
        this.fixed = fixed;
        this.pricePerUnit = pricePerUnit;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "material_name")
    private String name;

    @Column(name = "material_unit")
    private String unit;

    @Column(name = "material_is_verified")
    private boolean deleted;

    @Column(name = "is_fixed")
    private boolean fixed;

    @Column(name = "material_price_per_unit")
    private int pricePerUnit;
}
