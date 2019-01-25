package app.repository;

import app.model.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Integer> {

    @Query("select ua from UserAuthority ua where ua.user_id = ?1")
    List<UserAuthority> getAllByUser_id(Integer userId);
}