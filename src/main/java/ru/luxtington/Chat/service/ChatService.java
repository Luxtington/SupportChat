package ru.luxtington.Chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.luxtington.Chat.exception.UserNotFoundException;
import ru.luxtington.Chat.model.Chat;
import ru.luxtington.Chat.model.User;
import ru.luxtington.Chat.repository.ChatRepository;

import java.util.List;

@Service
public class ChatService {
    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public List<Chat> findAll(){
        return chatRepository.findAll();
    }

    public Chat findById(Integer id) {
        return chatRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public void save(Chat chat){
        chatRepository.save(chat);
    }

    @Transactional
    public void save(Chat chat, Integer id){
        chat.setId(id);
        chatRepository.save(chat);
    }

    @Transactional
    public void deleteById(Integer id){
        chatRepository.deleteById(id);
    }
}
