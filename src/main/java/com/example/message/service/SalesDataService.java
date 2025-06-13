package com.example.message.service;

import com.example.message.entity.SalesData;
import com.example.message.repository.SalesDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesDataService {
    private final SalesDataRepository repository;

    public SalesDataService(SalesDataRepository repository) {
        this.repository = repository;
    }

    public List<SalesData> getAllSalesData() {
        return repository.findAll();
    }
}
