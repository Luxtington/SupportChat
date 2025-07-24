package ru.luxtington.Chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.luxtington.Chat.exception.UserNotFoundException;
import ru.luxtington.Chat.model.User;
import ru.luxtington.Chat.repository.RoleRepository;
import ru.luxtington.Chat.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public List<User> findAllManagers(){
        List<User> users = findAll();
        List<User> managers = new ArrayList<>();
        for (User u : users){
            if (u.getRoles().contains(roleRepository.findByName("ROLE_MANAGER").orElse(null))){
                managers.add(u);
            }
        }
        return managers;
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public void save(User user){
        user.addRoleToUser(roleRepository.findByName("ROLE_USER").orElse(null));
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

    @Transactional
    public void assignManagerRoleToUser(Integer userId){
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.addRoleToUser(roleRepository.findByName("ROLE_MANAGER").orElse(null));
    }
}
