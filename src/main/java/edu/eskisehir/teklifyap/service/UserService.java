package edu.eskisehir.teklifyap.service;

import edu.eskisehir.teklifyap.model.User;
import edu.eskisehir.teklifyap.repository.UserDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserDao userDao;

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

}
