package ru.luxtington.Chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.luxtington.Chat.model.Chat;
import ru.luxtington.Chat.model.User;

import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    Optional<Chat> findByAuthorAndInterlocutor(User author, User interlocutor);
}
