package ru.luxtington.Chat.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@ToString(exclude = "createdAt")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private int id;
    @NotNull
    @Size(min = 1, max = 20)
    private String surname;
    @NotNull
    @Size(min = 2, max = 15)
    private String name;
    @Size(min = 5, max = 20)
    private String patronymic;
    @Range(min = 18, max = 120)
    private Integer age;
    @CreationTimestamp()
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "user")
    private List<Chat> allChatsWhereUserIsAuthor = new ArrayList<>();
    @OneToMany(mappedBy = "interlocutor")
    private List<Chat> allChatsWhereUserIsInterlocutor = new ArrayList<>();
    @OneToMany(mappedBy = "author")
    private List<Message> allUserMessages = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns =@JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User(String surname, String name, String patronymic, int age) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.age = age;
    }
}
