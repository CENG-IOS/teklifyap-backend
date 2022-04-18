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
        return created.getUser_id();
    }

    public User getUserProfile(User user) {
        return new User(userDao.getById(user.getUser_id()).getUser_id(), userDao.getById(user.getUser_id()).getUser_name(), userDao.getById(user.getUser_id()).getUser_surname(), userDao.getById(user.getUser_id()).getUser_email(), userDao.getById(user.getUser_id()).getUser_password(), userDao.getById(user.getUser_id()).getUser_creation_date());
    }

    public User getByid(int id) {
        return this.userDao.getById(id);
    }

    public void save(User user) {
        User updated = userDao.save(user);
    }

}
