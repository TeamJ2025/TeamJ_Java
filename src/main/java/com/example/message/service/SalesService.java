package com.example.message.service;

import com.example.message.entity.Sales;
import com.example.message.repository.SalesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {
    private final SalesRepository salesRepository;

    public SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public void saveAll(List<Sales> salesList) {
        salesRepository.saveAll(salesList);
    }

    public void save(Sales sales) {
        salesRepository.save(sales);
    }
}
