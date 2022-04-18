package edu.eskisehir.teklifyap.repository;

import edu.eskisehir.teklifyap.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferDao extends JpaRepository<Offer, Integer> {
    @Query(value = "select * from offer where user_id = :id", nativeQuery = true)
    List<Offer> getOffersByUserID(int id);
}
