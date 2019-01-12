package app.repository;

import app.model.Authority;
import app.model.UserRoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import app.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.lastname like ?1")
    List<User> findUsersByLastnameContaining(String s);

    @Query("select u from User u where u.pesel like ?1")
    User findUserByPeselContaining(String s);

    @Query("select u from User u where u.email = ?1")
    User findUserByEmail(String email);
}
