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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Size(min = 0, max = 20, message = "Размер отчества должен быть в диапазоне от 5 от до 20 символов")
    private String patronymic;
    @Range(min = 1910, max = 2025, message = "Год рождения должен быть в диапазоне от 1910 от до 2025 года")
    private Integer birthdayYear;
    @NotEmpty(message = "Логин не может быть пустым")
    @NotNull
    @Size(min = 1, max = 30, message = "Размер логина должен быть в диапазоне от 1 от до 30 символов")
    private String username;
    @NotEmpty(message = "Пароль не может быть пустым")
    @NotNull
    @Size(min = 1, max = 100, message = "Размер пароля должен быть в диапазоне от 1 от до 30 символов")
    private String password;
    @CreationTimestamp()
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns =@JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User(String surname, String name, String patronymic, Integer birthdayYear, String username, String password) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthdayYear = birthdayYear;
        this.username = username;
        this.password = password;
    }

    public void addRoleToUser(Role role){
        roles.add(role);
    }

    public boolean isAdmin(){
        return roles.stream().anyMatch(role -> role.getName().toUpperCase().equals("ROLE_ADMIN"));
    }

    public boolean isManager(){
        return roles.stream().anyMatch(role -> role.getName().toUpperCase().equals("ROLE_MANAGER"));
    }
}
