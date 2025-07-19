package ru.luxtington.Chat.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
    private Integer id;
    @NotEmpty(message = "Фамилия не может быть пустой")
    @NotNull
    @Size(min = 1, max = 30, message = "Размер фамилии должен быть в диапазоне от 1 от до 30 символов")
    private String surname;
    @NotEmpty(message = "Имя не может быть пустым")
    @NotNull
    @Size(min = 2, max = 15, message = "Размер имени должен быть в диапазоне от 2 от до 15 символов")
    private String name;
    @Size(min = 5, max = 20, message = "Размер отчества должен быть в диапазоне от 5 от до 20 символов")
    private String patronymic;
    @Range(min = 18, max = 120, message = "Возраст должен быть в диапазоне от 18 от до 120 лет")
    private Integer age;
    @NotEmpty(message = "Логин не может быть пустым")
    @NotNull
    @Size(min = 1, max = 30, message = "Размер логина должен быть в диапазоне от 1 от до 30 символов")
    private String username;
    @NotEmpty(message = "Пароль не может быть пустым")
    @NotNull
    @Size(min = 1, max = 30, message = "Размер пароля должен быть в диапазоне от 1 от до 30 символов")
    private String password;
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
