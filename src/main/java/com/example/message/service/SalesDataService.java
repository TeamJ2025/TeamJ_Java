package com.example.message.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.message.entity.Beer;

// 一時的にコメントアウト
/*
import com.example.message.entity.SalesData;
import com.example.message.repository.SalesDataRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalesDataService {

    private final SalesDataRepository repository;

    public SalesDataService(SalesDataRepository repository) {
        this.repository = repository;
    }

    public List<SalesData> getAllSalesData() {
        List<SalesData> salesDataList = repository.findAllByOrderBySaleDayDesc();
        return salesDataList.isEmpty() ? new ArrayList<>() : salesDataList;
    }
}
*/

import com.example.message.entity.Sales;
import com.example.message.repository.BeerRepository;
import com.example.message.repository.SalesRepository;

@Service
public class SalesDataService {

    private final SalesRepository salesRepository;
    private final BeerRepository beerRepository;

    public SalesDataService(SalesRepository salesRepository, BeerRepository beerRepository) {
        this.salesRepository = salesRepository;
        this.beerRepository = beerRepository;
    }

    // 全ての販売データを取得（日付降順）- 既存のfindAllByOrderBySaleDayDesc()に対応
    public List<Sales> getAllSalesData() {
        return salesRepository.findAllByOrderBySalesDateDesc();
    }

    // 全てのビール情報を取得
    public List<Beer> getAllBeers() {
        return beerRepository.findByIsDeletedFalse();
    }

    // ビール情報とユーザー情報を結合した販売データを取得
    public List<Sales> getAllSalesWithDetails() {
        return salesRepository.findAll(); // JOIN FETCHでビール・ユーザー情報も取得
    }
    public List<Sales> getSalesByDate(LocalDate date) {
        return salesRepository.findBySalesDate(date);
    }
    public void saveSales(Sales sales) {
        salesRepository.save(sales);
    }
    
}