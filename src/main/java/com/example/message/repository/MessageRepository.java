package com.example.message.repository;

import com.example.message.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    Optional<Message> findByEmail(String email);
}