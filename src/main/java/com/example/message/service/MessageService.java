package com.example.message.service;

import com.example.message.model.Message;
import com.example.message.repository.MessageRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // インスタンス化
    }

    public List<Message> getAllMessages(){
        return repository.findAll();
    }

    public void addMessage(String name, String email, String password){
        String hashedPassword = passwordEncoder.encode(password);
        repository.save(new Message(name, email, hashedPassword));
    }
}
