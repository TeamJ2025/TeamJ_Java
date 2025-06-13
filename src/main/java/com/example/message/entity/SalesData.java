package com.example.message.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sales_date")
public class SalesData {

    @Id
    @Column(name = "sale_day")
    private LocalDate saleDay;

    @Column(name = "total_sales_yen")
    private Integer totalSalesYen;

    @Column(name = "total_cups")
    private Integer totalCups;

    // --- getter & setter ---
    public LocalDate getSaleDay() {
        return saleDay;
    }

    public void setSaleDay(LocalDate saleDay) {
        this.saleDay = saleDay;
    }

    public Integer getTotalSalesYen() {
        return totalSalesYen;
    }

    public void setTotalSalesYen(Integer totalSalesYen) {
        this.totalSalesYen = totalSalesYen;
    }

    public Integer getTotalCups() {
        return totalCups;
    }

    public void setTotalCups(Integer totalCups) {
        this.totalCups = totalCups;
    }
}
