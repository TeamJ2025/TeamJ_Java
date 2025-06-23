package com.example.message.repository;

import com.example.message.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    Optional<Message> findByEmail(String email);

     // 論理削除対応メソッド（@Queryを明示的に使用）
    @Query("SELECT m FROM Message m WHERE m.isDeleted = false")
    List<Message> findByIsDeletedFalse();
    
    @Query("SELECT m FROM Message m WHERE m.id = :id AND m.isDeleted = false")
    Optional<Message> findByIdAndIsDeletedFalse(@Param("id") Integer id);
}