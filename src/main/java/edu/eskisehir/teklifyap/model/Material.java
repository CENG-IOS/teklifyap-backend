package edu.eskisehir.teklifyap.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.eskisehir.teklifyap.model.request.AddingMaterialRequest;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "material")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Material implements Serializable {

    public Material(AddingMaterialRequest request) {
        this.name = request.getName();
        this.unit = request.getUnit();
        this.pricePerUnit = request.getPricePerUnit();
        this.deleted = false;
        this.fixed = false;
    }

    public Material(int id, String name, String unit, boolean deleted, boolean fixed, int pricePerUnit) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.deleted = deleted;
        this.fixed = fixed;
        this.pricePerUnit = pricePerUnit;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "material", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @ToString.Exclude
    private List<OfferMaterial> materials;

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
