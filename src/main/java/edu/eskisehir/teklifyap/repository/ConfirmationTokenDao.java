package edu.eskisehir.teklifyap.repository;

import edu.eskisehir.teklifyap.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationTokenDao extends JpaRepository<ConfirmationToken, Integer> {

    Optional<ConfirmationToken> findByTokenAndMail(String token, String mail);
}
