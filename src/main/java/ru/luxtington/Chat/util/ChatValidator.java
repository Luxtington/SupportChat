package ru.luxtington.Chat.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.luxtington.Chat.model.Chat;
import ru.luxtington.Chat.repository.ChatRepository;

@Component
public class ChatValidator implements Validator {

    private final ChatRepository chatRepository;

    @Autowired
    public ChatValidator(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Chat.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Chat chat = (Chat) target;

        if (chatRepository.findByAuthorAndInterlocutor(chat.getAuthor(), chat.getInterlocutor()).isPresent()){
            errors.rejectValue("interlocutor", "", "Чат с данным пользователем уже существует");
        }
    }
}
