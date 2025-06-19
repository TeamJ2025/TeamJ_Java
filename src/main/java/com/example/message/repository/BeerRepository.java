package com.example.message.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.message.entity.Beer;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Integer> {
    
    // 削除されていないビール一覧を取得
    List<Beer> findByIsDeletedFalse();
    
    // IDで削除されていないビールを取得
    Optional<Beer> findByIdAndIsDeletedFalse(Integer id);
    
    // ビール名で検索（削除されていないもの）
    Optional<Beer> findByBeerNameAndIsDeletedFalse(String beerName);
}

