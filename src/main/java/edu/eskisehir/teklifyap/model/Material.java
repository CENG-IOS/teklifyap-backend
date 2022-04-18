package edu.eskisehir.teklifyap.model;

import lombok.*;

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
    private int material_id;

    @OneToOne
    @JoinColumn(name = "user_id")
//    @JsonIgnore
    private User user;

    @Column(name = "material_name")
    private String material_name;

    @Column(name = "material_unit")
    private String material_unit;

    @Column(name = "material_is_verified")
    private short material_is_verified;

    @Column(name = "is_fixed")
    private short is_fixed;

    @Column(name = "material_price_per_unit")
    private int material_price_per_unit;



}
