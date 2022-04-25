package edu.eskisehir.teklifyap.repository;

import edu.eskisehir.teklifyap.model.Offer;
import edu.eskisehir.teklifyap.model.ShortOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferDao extends JpaRepository<Offer, Integer> {

    @Query(value = "select new edu.eskisehir.teklifyap.model.ShortOffer(o) from Offer o where o.user.id = :id")
    List<ShortOffer> getOffersByUserID(int id);
}
