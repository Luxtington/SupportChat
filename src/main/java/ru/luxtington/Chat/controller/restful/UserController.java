package ru.luxtington.Chat.controller.restful;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.luxtington.Chat.exception.UserNotFoundException;
import ru.luxtington.Chat.model.User;
import ru.luxtington.Chat.service.UserService;
import ru.luxtington.Chat.util.UserValidator;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/lu/users")
public class UserController {

    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping()
    public ResponseEntity<List<User>> findAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("/new")
    public ResponseEntity<?> createUser(@RequestBody @Valid User user,
                                           BindingResult bindingResult) throws URISyntaxException {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        userService.save(user);
        return ResponseEntity.created(new URI("/api/lu/users/new")).build();
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<Void> editUser(@PathVariable("id") Integer id, @RequestBody @Valid User user){
        userService.save(user, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(UserNotFoundException e){
        return new ResponseEntity<>(new UserNotFoundException().getMessage(), HttpStatus.NOT_FOUND);
    }
}
