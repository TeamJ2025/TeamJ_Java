package com.example.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.message.entity.SalesData;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceRepository extends JpaRepository<SalesData, Long> {
}

