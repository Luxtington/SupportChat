package ru.luxtington.Chat.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.luxtington.Chat.dto.ChatUserDetails;
import ru.luxtington.Chat.exception.UserNotFoundException;
import ru.luxtington.Chat.model.User;
import ru.luxtington.Chat.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class ChatUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public ChatUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        Hibernate.initialize(user.getRoles());
        return new ChatUserDetails(user);
    }
}
