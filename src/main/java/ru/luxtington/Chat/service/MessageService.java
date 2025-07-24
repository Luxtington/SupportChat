package ru.luxtington.Chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.luxtington.Chat.exception.ChatNotFoundException;
import ru.luxtington.Chat.exception.UserNotFoundException;
import ru.luxtington.Chat.model.Chat;
import ru.luxtington.Chat.model.Message;
import ru.luxtington.Chat.model.User;
import ru.luxtington.Chat.repository.ChatRepository;
import ru.luxtington.Chat.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, ChatRepository chatRepository) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
    }

    public List<Message> findAll(){
        return messageRepository.findAll();
    }

    public Message findById(Integer id) {
        return messageRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public void save(Message message){
        messageRepository.save(message);
    }

    @Transactional
    public void save(Message message, Integer id){
        message.setId(id);
        messageRepository.save(message);
    }

    @Transactional
    public void deleteById(Integer id){
        messageRepository.deleteById(id);
    }

    public List<Message> findMessagesByChatId(Integer id){
        Chat chat = chatRepository.findById(id).orElseThrow(ChatNotFoundException::new);
        return messageRepository.findByChat(chat);
    }
}
