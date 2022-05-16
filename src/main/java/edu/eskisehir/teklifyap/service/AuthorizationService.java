package edu.eskisehir.teklifyap.service;

import edu.eskisehir.teklifyap.config.security.JwtTokenUtil;
import edu.eskisehir.teklifyap.model.User;
import edu.eskisehir.teklifyap.repository.UserDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
@Slf4j
public class AuthorizationService {

    private final UserDao userDao;
    private final JwtTokenUtil jwtTokenUtil;

    public User checkSelf(HttpServletRequest request, int id) throws Exception {
        User user = retrieveUserFromHttpRequest(request);

        boolean status = user.getId() == id;
        log.info("checkSelf(" + user.getId() + ") -> " + status);

        if (!status)
            throw new Exception("NoPermissionException");
        return user;
    }


    public User retrieveUserFromHttpRequest(HttpServletRequest request) throws Exception {
        String token = request.getHeader("Authorization").substring(7);
        return userDao.findByMail(jwtTokenUtil.getUsernameFromToken(token))
                .orElseThrow(() -> new Exception("UserNotFoundException"));
    }
}
