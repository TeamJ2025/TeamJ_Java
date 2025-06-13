package com.example.message.service;

import com.example.message.model.Message;
import com.example.message.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository repository;

    public MessageService(MessageRepository repository){
        this.repository = repository;
    }

    public List<Message> getAllMessages(){
        return repository.findAll();
    }

    public void addMessage(String username, String password){
        repository.save(new Message(username,password));
    }
}