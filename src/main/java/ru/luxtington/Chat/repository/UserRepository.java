package ru.luxtington.Chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.luxtington.Chat.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :role_name")
    List<User> findByRoleName(@Param("role_name") String roleName);
}
