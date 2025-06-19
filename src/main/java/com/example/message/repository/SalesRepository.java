package com.example.message.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.message.entity.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer> {
    
    // 指定日の販売データを取得
    List<Sales> findBySalesDate(LocalDate salesDate);
    
    // 全販売データを日付降順で取得（SalesDataRepository.findAllByOrderBySaleDayDesc()に対応）
    List<Sales> findAllByOrderBySalesDateDesc();
    
    // 特定ユーザーの販売データを取得
    List<Sales> findByUsersIdOrderBySalesDateDesc(Integer usersId);
    
    // 特定ビールの販売データを取得
    List<Sales> findByBeersIdOrderBySalesDateDesc(Integer beersId);
}
