package edu.eskisehir.teklifyap.service;

import edu.eskisehir.teklifyap.model.Material;
import edu.eskisehir.teklifyap.model.response.ShortMaterialResponse;
import edu.eskisehir.teklifyap.repository.MaterialDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MaterialService {
    private final MaterialDao materialDao;

    public Material findById(int mid) throws Exception {
        return materialDao.findById(mid).orElseThrow(() -> new Exception("MaterialNotFoundException"));
    }

    public List<ShortMaterialResponse> getAll() {
        return materialDao.findAllShortMaterial();
    }

    public List<ShortMaterialResponse> getMaterials(int id) {
        return materialDao.findByUserId(id);
    }

    public void delete(String id) throws Exception {
        JSONParser jsonParser = new JSONParser(id);
        BigInteger parsedID = (BigInteger) jsonParser.object().get("deleted");
        Material material = materialDao.getById(parsedID.intValue());
        material.setDeleted(false);
        materialDao.save(material);
        materialDao.delete(findById(Integer.parseInt(id)));
    }

    public Material save(Material material) {
        return materialDao.save(material);
    }

    public List<Material> adds(List<Material> materials) {
        return materialDao.saveAll(materials);
    }

    public Material update(Material material) {
        return materialDao.save(material);
    }


    public List<ShortMaterialResponse> findByUserId(int uid) {
        return materialDao.findByUserId(uid);
    }
}
