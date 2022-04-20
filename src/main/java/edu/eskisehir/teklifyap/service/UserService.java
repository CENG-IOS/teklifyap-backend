package edu.eskisehir.teklifyap.service;

import edu.eskisehir.teklifyap.config.security.PasswordEncoder;
import edu.eskisehir.teklifyap.model.response.ShortUserResponse;
import edu.eskisehir.teklifyap.model.User;
import edu.eskisehir.teklifyap.model.response.ShortUserResponse;
import edu.eskisehir.teklifyap.repository.UserDao;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public User findByMail(String mail) throws Exception {
        return userDao.findByMail(mail).orElseThrow(() -> new Exception("UserNotFoundException"));
    }

    public List<ShortUserResponse> findAllShortUser() {
        return userDao.findAllShortUser();
    }

    public int add(User user) {
        User created;
        created = this.userDao.save(user);
        return created.getId();
    }

    public User getUserProfile(User user) {
        return new User(userDao.getById(user.getId()).getId(), userDao.getById(user.getId()).getName(), userDao.getById(user.getId()).getSurname(), userDao.getById(user.getId()).getMail(), userDao.getById(user.getId()).getPassword(), userDao.getById(user.getId()).getCreationDate());
    }

    public User getById(int id) {
        return this.userDao.getById(id);
    }

    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByMail(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public User create(User user) throws Exception {
        User userDetails = null;
        try {
            userDetails = findByMail(user.getMail());
        } catch (Exception ignored) {
        }
        if (userDetails != null)
            throw new Exception("ExistingUserException");
        user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword()));
        user.setCreationDate(LocalDateTime.now());
        user.setConfirmed(false);
        return userDao.save(user);
    }

    public User findById(int uid) throws Exception {
        return userDao.findById(uid).orElseThrow(() -> new Exception("UserNotFoundException"));
    }
}
