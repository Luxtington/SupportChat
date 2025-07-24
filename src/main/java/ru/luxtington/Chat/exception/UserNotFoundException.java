package ru.luxtington.Chat.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
