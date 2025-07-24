package ru.luxtington.Chat.exception;

public class ChatNotFoundException extends RuntimeException {
    public ChatNotFoundException() {
    }

    public ChatNotFoundException(String message) {
        super(message);
    }
}
