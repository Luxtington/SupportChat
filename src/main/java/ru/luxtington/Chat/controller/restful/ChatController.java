package ru.luxtington.Chat.controller.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.luxtington.Chat.exception.ChatNotFoundException;
import ru.luxtington.Chat.model.Chat;
import ru.luxtington.Chat.service.ChatService;
import ru.luxtington.Chat.dto.MessageDto;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/lu/chats")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping()
    public ResponseEntity<List<Chat>> findAllChats(){
        return ResponseEntity.ok(chatService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chat> findChatById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(chatService.findById(id));
    }

    @PostMapping("/new")
    public ResponseEntity<?> createChat(@RequestParam Integer creatorId,
                                        @RequestParam Integer interlocutorId,
                                        BindingResult bindingResult) throws URISyntaxException {

        chatService.save(creatorId, interlocutorId);
        return ResponseEntity.created(new URI("/api/lu/chat/new")).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable("id") Integer id){
        chatService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/send")
    public ResponseEntity<Void> sendMessageInChat(@PathVariable("id") Integer chatId,
                                                  @RequestParam Integer authorId,
                                                  @RequestBody MessageDto holder) throws URISyntaxException {
        chatService.saveMessageInChat(chatId, authorId, holder.getText());
        return ResponseEntity.created(new URI("/api/lu/chats/" + chatId.toString() + "/send/" + authorId.toString())).build();
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(ChatNotFoundException e){
        return new ResponseEntity<>(new ChatNotFoundException().getMessage(), HttpStatus.NOT_FOUND);
    }

}
