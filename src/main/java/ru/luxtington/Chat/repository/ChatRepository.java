package ru.luxtington.Chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.luxtington.Chat.model.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
}
