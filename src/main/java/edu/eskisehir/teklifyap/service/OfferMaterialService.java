package edu.eskisehir.teklifyap.service;

import edu.eskisehir.teklifyap.repository.OfferMaterialDao;
import edu.eskisehir.teklifyap.model.OfferMaterial;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OfferMaterialService {

    private final OfferMaterialDao offerMaterialDao;

    public List<OfferMaterial> getAll() {
        return offerMaterialDao.findAll();
    }

    public void makeOffer(OfferMaterial offerMaterial) {
        this.offerMaterialDao.save(offerMaterial);
    }

    public void makeOffers(List<OfferMaterial> offerMaterials) {
        for (OfferMaterial offerMaterial : offerMaterials) {
            makeOffer(offerMaterial);
        }
    }

    public List<OfferMaterial> getMaterialsByOffer(int id) {
        List<OfferMaterial> offerMaterial = offerMaterialDao.getMaterialsByOffer(id);
        return offerMaterial;
    }
}
