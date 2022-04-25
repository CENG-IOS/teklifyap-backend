package edu.eskisehir.teklifyap.repository;


import edu.eskisehir.teklifyap.model.User;
import edu.eskisehir.teklifyap.model.response.ShortUserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {

    Optional<User> findByMail(String username);

    @Query("select new edu.eskisehir.teklifyap.model.response.ShortUserResponse(user) from User user")
    List<ShortUserResponse> findAllShortUser();
}
