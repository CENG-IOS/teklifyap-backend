package edu.eskisehir.teklifyap.service;

import edu.eskisehir.teklifyap.model.Material;
import edu.eskisehir.teklifyap.model.Offer;
import edu.eskisehir.teklifyap.model.OfferMaterial;
import edu.eskisehir.teklifyap.model.User;
import edu.eskisehir.teklifyap.model.request.MakingOfferRequest;
import edu.eskisehir.teklifyap.repository.OfferDao;
import edu.eskisehir.teklifyap.repository.OfferMaterialDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class OfferMaterialService {

    private final OfferMaterialDao offerMaterialDao;
    private final OfferDao offerDao;
    private final UserService userService;
    private final MaterialService materialService;

    public List<OfferMaterial> getAll() {
        return offerMaterialDao.findAll();
    }

    public void makeOffer(MakingOfferRequest request, int uid) throws Exception {

        User user = userService.findById(uid);

        Offer offer = new Offer();
        offer.setUsername(request.getUserName());
        offer.setTitle(request.getTitle());
        offer.setCompanyName(request.getToWho());
        offer.setDate(request.getDate());
        offer.setProfitRate(request.getProfitRate());
        offer.setStatus(Offer.OfferStatus.PENDING);
        offer.setTotalPrice(request.getTotalPrice());
        offer.setUser(user);

        offer = offerDao.save(offer);

        List<OfferMaterial> offerMaterials = new LinkedList<>();
        Offer finalOffer = offer;
        Offer finalOffer1 = offer;
        request.getMaterials().parallelStream().forEach(materialWithPrice -> {

            Material material = null;
            try {
                material = materialService.findById(materialWithPrice.getId());
            } catch (Exception e) {
                offerDao.delete(finalOffer1);
            }
            OfferMaterial offerMaterial = new OfferMaterial(material, finalOffer, materialWithPrice.getPricePerUnit());
            offerMaterials.add(offerMaterial);
        });

        saveAll(offerMaterials);

    }

    public List<OfferMaterial> getMaterialsByOffer(int id) {
        return offerMaterialDao.getMaterialsByOffer(id);
    }

    public List<OfferMaterial> saveAll(List<OfferMaterial> offerMaterials) {
        return offerMaterialDao.saveAll(offerMaterials);
    }
}
