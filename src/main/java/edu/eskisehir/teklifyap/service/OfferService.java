package edu.eskisehir.teklifyap.service;

import edu.eskisehir.teklifyap.model.Offer;
import edu.eskisehir.teklifyap.model.ShortOffer;
import edu.eskisehir.teklifyap.repository.OfferDao;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OfferService {

    private final OfferDao offerDao;

    public List<ShortOffer> getOffersByUser(int uid, PageRequest page) {

        return offerDao.getOffersByUserID(uid);
    }

    public Offer findById(int oid) throws Exception {
        return offerDao.findById(oid).orElseThrow(() -> new Exception("OfferNotFoundException"));
    }

    public Offer save(Offer offer) {
        return offerDao.save(offer);
    }

    public void delete(int fid) {
        offerDao.deleteById(fid);
    }
}
