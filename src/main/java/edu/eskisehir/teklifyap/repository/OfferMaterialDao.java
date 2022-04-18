package edu.eskisehir.teklifyap.repository;

import edu.eskisehir.teklifyap.model.OfferMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferMaterialDao extends JpaRepository<OfferMaterial,Integer> {
    @Query(value = "select * from offer_material where offer_id = :id" , nativeQuery = true)
    List<OfferMaterial> getMaterialsByOffer(int id);
}
