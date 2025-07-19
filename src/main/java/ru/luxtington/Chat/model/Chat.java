package ru.luxtington.Chat.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "interlocutor_id", referencedColumnName = "id")
    private User interlocutor;
    @OneToMany(mappedBy = "chat")
    private List<Message> allMessages = new ArrayList<>();

    public Chat(User user, User interlocutor) {
        this.user = user;
        this.interlocutor = interlocutor;
    }
}
