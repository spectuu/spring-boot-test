package me.itoxic.repository;

import me.itoxic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, Serializable {

    List<User> findAll();

    User findByPasswordAndEmail(String password, String email);

    User findByEmail(String email);

    User findById(long id);

    List<User> findAllByCoins(int coins);

    List<User> findByPassword(String password);

}
