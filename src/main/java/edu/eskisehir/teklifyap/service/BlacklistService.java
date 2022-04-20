package edu.eskisehir.teklifyap.service;

import edu.eskisehir.teklifyap.model.JWTBlacklist;
import edu.eskisehir.teklifyap.repository.JWTBlacklistDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class BlacklistService {

    private JWTBlacklistDao jwtBlacklistDao;

    private static int blackListCounter = 0;

    public Boolean isBlacklisted(String token) {
        Optional<JWTBlacklist> blacklistedItem = jwtBlacklistDao.findByToken(token);
        Boolean value;
        if (!blacklistedItem.isPresent())
            value = false;
        else
            value = !deleteIfExpired(token);
        if (value)
            log.error("isBlacklisted(" + token + ") -> " + true);
        else
            log.info("isBlacklisted(" + token + ") -> " + false);
        return value;
    }

    public void insertJWTtoBlacklist(String token) throws Exception {
        blackListCounter++;
        Optional<JWTBlacklist> item = jwtBlacklistDao.findByToken(token);
        log.info("getToken(" + token + ") -> setBlacklisted(true)");

        if (!item.isPresent()) {
            jwtBlacklistDao.save(new JWTBlacklist(token));
        } else if (item.get().getTimestamp().plusDays(1).isAfter(LocalDateTime.now())) {
            item.get().setTimestamp(LocalDateTime.now());
            jwtBlacklistDao.save(item.get());
        }
        if (blackListCounter > 10000) {
            cleanList();
            blackListCounter = 0;
        }
    }

    public Boolean deleteIfExpired(String token) {
        Boolean status = false;
        Optional<JWTBlacklist> item = jwtBlacklistDao.findByToken(token);
        if (item.isPresent() && item.get().getTimestamp().plusDays(1).isBefore(LocalDateTime.now())) {
            jwtBlacklistDao.delete(item.get());
            status = true;
        }
        log.info("isExpired(" + token + ") -> " + status);

        return status;

    }

    public void cleanList() {
        log.info("forEach(blacklist.items) -> cleanItem()");

        List<JWTBlacklist> list = jwtBlacklistDao.findAll();
        for (JWTBlacklist iter : list)
            deleteIfExpired(iter.getToken());
    }

}
