package edu.eskisehir.teklifyap.service;

import edu.eskisehir.teklifyap.config.security.PasswordEncoder;
import edu.eskisehir.teklifyap.model.User;
import edu.eskisehir.teklifyap.repository.UserDao;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public Optional<UserDetails> findByMail(String mail) {
        return userDao.findByMail(mail);
    }

    public List<User> getAll() {
        return userDao.findAll();
    }

    public int add(User user) {
        User created;
        created = this.userDao.save(user);
        return created.getId();
    }

    public User getUserProfile(User user) {
        return new User(userDao.getById(user.getId()).getId(), userDao.getById(user.getId()).getName(), userDao.getById(user.getId()).getSurname(), userDao.getById(user.getId()).getMail(), userDao.getById(user.getId()).getPassword(), userDao.getById(user.getId()).getCreationDate());
    }

    public User getByid(int id) {
        return this.userDao.getById(id);
    }

    public void save(User user) {
        User updated = userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByMail(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public User create(User user) throws Exception {
        Optional<UserDetails> userDetails = findByMail(user.getMail());
        if (userDetails.isPresent())
            throw new Exception("ExistingUserException");
        user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword()));
        user.setCreationDate(LocalDateTime.now());
        user.setConfirmed(false);
        return userDao.save(user);
    }
}
