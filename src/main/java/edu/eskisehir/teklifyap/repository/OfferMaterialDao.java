package edu.eskisehir.teklifyap.repository;

import edu.eskisehir.teklifyap.model.OfferMaterial;
import edu.eskisehir.teklifyap.model.ShortOfferMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferMaterialDao extends JpaRepository<OfferMaterial, Integer> {

    @Query(value = "select new edu.eskisehir.teklifyap.model.ShortOfferMaterial(om.id,om.offer.id,om.material,om.unitPrice) from OfferMaterial om where om.offer.id = :id")
    List<ShortOfferMaterial> getMaterialsByOffer(int id);
}
