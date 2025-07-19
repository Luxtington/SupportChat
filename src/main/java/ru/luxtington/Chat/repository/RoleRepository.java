package ru.luxtington.Chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.luxtington.Chat.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
