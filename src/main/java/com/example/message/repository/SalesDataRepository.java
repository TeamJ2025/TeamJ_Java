package com.example.message.repository;

import com.example.message.entity.SalesData;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;


public interface SalesDataRepository extends JpaRepository<SalesData, LocalDate> {
}
