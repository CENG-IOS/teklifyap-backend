package edu.eskisehir.teklifyap.repository;


import edu.eskisehir.teklifyap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {


}
