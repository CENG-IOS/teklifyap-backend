package edu.eskisehir.teklifyap.service;

import edu.eskisehir.teklifyap.repository.OfferDao;
import edu.eskisehir.teklifyap.model.Offer;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OfferService {

    private final OfferDao offerDao;

    public List<Offer> getAll() {
        return offerDao.findAll();
    }

    public List<Offer> getOffersByUser(String id) throws ParseException {
        JSONParser jsonParser = new JSONParser(id);
        int a = Integer.parseInt((String) jsonParser.parseObject().get("id"));
        return offerDao.getOffersByUserID(a);
    }

    public void updateStatus(int id, Offer.OfferStatus status) {
        Offer offer = offerDao.getById(id);
        offer.setStatus(status);
        offerDao.save(offer);
    }

    public int makeOffer(Offer offer) {
        System.out.println(offer.toString());
        offerDao.save(offer);
        return offer.getId();
    }

    public Offer findById(int fid) throws Exception {
        return offerDao.findById(fid).orElseThrow(() -> new Exception("OfferNotFoundException"));
    }
}
