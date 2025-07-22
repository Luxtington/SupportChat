package ru.luxtington.Chat.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;
    @ManyToOne
    @JoinColumn(name = "interlocutor_id", referencedColumnName = "id")
    private User interlocutor;

    public Chat(User author, User interlocutor) {
        this.author = author;
        this.interlocutor = interlocutor;
    }

    public void addMessage(@NotNull Message message){
        message.setChat(this);
    }
}
