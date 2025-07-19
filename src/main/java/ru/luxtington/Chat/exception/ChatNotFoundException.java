package ru.luxtington.Chat.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ChatNotFoundException extends RuntimeException {
    private String message;
}
