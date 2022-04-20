package edu.eskisehir.teklifyap.repository;


import edu.eskisehir.teklifyap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {

    Optional<UserDetails> findByMail(String username);
}
