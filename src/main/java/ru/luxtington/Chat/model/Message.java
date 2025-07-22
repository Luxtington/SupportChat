package ru.luxtington.Chat.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "chat_id", referencedColumnName = "id")
    private Chat chat;
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;
    @NotNull
    @Size(min = 1, max = 120, message = "Сообщение должно содержать как минимум 1 символ, максимум - 120 символов")
    @Column(name = "message_text")
    private String text;
    @CreationTimestamp()
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public Message(Chat chat, User author, String text) {
        this.chat = chat;
        this.author = author;
        this.text = text;
    }
}
