package edu.eskisehir.teklifyap.repository;

import edu.eskisehir.teklifyap.model.Material;
import edu.eskisehir.teklifyap.model.response.ShortMaterialResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaterialDao extends JpaRepository<Material, Integer> {
    @Query(value = "select * from material where user_id = :id and material_is_verified = 1", nativeQuery = true)
    List<Material> takeMaterialsByUserID(@Param("id") Integer id);

    @Query("select new edu.eskisehir.teklifyap.model.response.ShortMaterialResponse(m) from Material m where m.user.id = :uid")
    List<ShortMaterialResponse> findByUserId(int uid);

    @Query("select new edu.eskisehir.teklifyap.model.response.ShortMaterialResponse(m) from Material m")
    List<ShortMaterialResponse> findAllShortMaterial();

    Material deleteById(int id);

}
