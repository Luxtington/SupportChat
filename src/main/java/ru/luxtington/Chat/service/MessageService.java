package ru.luxtington.Chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.luxtington.Chat.exception.UserNotFoundException;
import ru.luxtington.Chat.model.Message;
import ru.luxtington.Chat.model.User;
import ru.luxtington.Chat.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
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
}
