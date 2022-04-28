package edu.eskisehir.teklifyap.service;

import edu.eskisehir.teklifyap.model.Material;
import edu.eskisehir.teklifyap.model.response.ShortMaterialResponse;
import edu.eskisehir.teklifyap.repository.MaterialDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.PublicKey;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MaterialService {
    private final MaterialDao materialDao;

    public Material findById(int mid) throws Exception {
        return materialDao.findById(mid).orElseThrow(() -> new Exception("MaterialNotFoundException"));
    }

    public List<ShortMaterialResponse> getMaterials(int id) {
        return materialDao.findByUserId(id);
    }

    public void delete(String id) throws Exception {
        materialDao.delete(findById(Integer.parseInt(id)));
    }

    public Material save(Material material) {
        return materialDao.save(material);
    }

    public Material update(Material material) {
        return materialDao.save(material);
    }

    public List<ShortMaterialResponse> findByUserId(int uid) {
        return materialDao.findByUserId(uid);
    }

    public void saveAll(List<Material> materials){
        materialDao.saveAll(materials);
    }
}
