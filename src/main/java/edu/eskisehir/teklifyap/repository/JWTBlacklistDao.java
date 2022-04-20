package edu.eskisehir.teklifyap.repository;

import edu.eskisehir.teklifyap.model.JWTBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JWTBlacklistDao extends JpaRepository<JWTBlacklist, String> {
    Optional<JWTBlacklist> findByToken(String token);
}
