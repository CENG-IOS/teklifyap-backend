package edu.eskisehir.teklifyap.repository;

import edu.eskisehir.teklifyap.model.Offer;
import edu.eskisehir.teklifyap.model.OfferMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferMaterialDao extends JpaRepository<OfferMaterial, Integer> {

    List<OfferMaterial> findByOffer(Offer offer);
}
