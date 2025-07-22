package ru.luxtington.Chat.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.luxtington.Chat.model.User;
import ru.luxtington.Chat.repository.UserRepository;

@Component
public class UserValidator implements Validator {

    private final UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (userRepository.findByUsername(user.getUsername()).isPresent()){
            errors.rejectValue("username", "", "Пользователь с таким логином уже зарегистрирован");
        }
    }
}
