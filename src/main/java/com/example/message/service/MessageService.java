package com.example.message.service;

import com.example.message.model.Message;
import com.example.message.repository.MessageRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class MessageService {

    private final MessageRepository repository;
    private final PasswordEncoder passwordEncoder;

    public MessageService(MessageRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    // public List<Message> getAllMessages(){
    //     return repository.findAll();
    // }

    public List<Message> getAllMessages() {
        return repository.findByIsDeletedFalse(); // 変更
    }

    public void addMessage(String name, String email, String password){
        System.out.println(">>> addMessage called with: " + name + ", " + email);
        String hashedPassword = passwordEncoder.encode(password);
        Message message = new Message();
        message.setName(name);
        message.setEmail(email);
        message.setPassword(hashedPassword);
        message.setRole("employee");
        message.setIsDeleted(false);
        message.setCreatedAt(LocalDateTime.now());
        message.setUpdatedAt(LocalDateTime.now());

        repository.save(message);
        System.out.println(">>> message saved");
    }

    
}


