package ru.luxtington.Chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.luxtington.Chat.exception.UserNotFoundException;
import ru.luxtington.Chat.model.User;
import ru.luxtington.Chat.repository.UserRepository;

import java.util.List;
import java.util.function.Supplier;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public void save(User user){
        userRepository.save(user);
    }

    @Transactional
    public void save(User user, Integer id){
        user.setId(id);
        userRepository.save(user);
    }

    @Transactional
    public void deleteById(Integer id){
        userRepository.deleteById(id);
    }
}
