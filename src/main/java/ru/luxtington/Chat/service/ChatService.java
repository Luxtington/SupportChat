package ru.luxtington.Chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.luxtington.Chat.exception.ChatNotFoundException;
import ru.luxtington.Chat.exception.UserNotFoundException;
import ru.luxtington.Chat.model.Chat;
import ru.luxtington.Chat.model.Message;
import ru.luxtington.Chat.repository.ChatRepository;
import ru.luxtington.Chat.repository.MessageRepository;
import ru.luxtington.Chat.repository.UserRepository;

import java.util.List;

@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository, UserRepository userRepository, MessageRepository messageRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    public List<Chat> findAll(){
        return chatRepository.findAll();
    }

    public Chat findById(Integer id) {
        return chatRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public void save(Chat chat, Integer chatId, Integer ownerId){
        chat.setAuthor(userRepository.findById(ownerId).orElse(null));
        chatRepository.save(chat);
    }

    @Transactional
    public void save(Integer creatorId, Integer interlocutorId){
        Chat chat = new Chat(
                userRepository.findById(creatorId).orElse(null),
                userRepository.findById(interlocutorId).orElse(null)
        );
        chatRepository.save(chat);
    }

    @Transactional
    public void deleteById(Integer id){
        chatRepository.deleteById(id);
    }

    @Transactional
    public void saveMessageInChat(Integer chatId, Integer authorId, String messageText){
        Chat chat = chatRepository.findById(chatId).orElseThrow(ChatNotFoundException::new);
        Message message = new Message(chat, userRepository.findById(authorId).orElse(null), messageText);
        messageRepository.save(message);
        chat.addMessage(message);
    }
}
