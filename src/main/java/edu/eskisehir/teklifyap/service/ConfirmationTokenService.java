package edu.eskisehir.teklifyap.service;

import edu.eskisehir.teklifyap.model.ConfirmationToken;
import edu.eskisehir.teklifyap.repository.ConfirmationTokenDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenDao confirmationTokenDao;

    public ConfirmationToken save(ConfirmationToken confirmationToken) {
        return confirmationTokenDao.save(confirmationToken);
    }

    public void delete(ConfirmationToken confirmationToken) {
        confirmationTokenDao.delete(confirmationToken);
    }

    public Optional<ConfirmationToken> validate(String token, String mail) {
        return confirmationTokenDao.findByTokenAndMail(token, mail);
    }
}
