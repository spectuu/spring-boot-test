package co.com.aruma.repository;

import co.com.aruma.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, Serializable {

    List<User> findAll();

    User findByEmail(String email);

}
