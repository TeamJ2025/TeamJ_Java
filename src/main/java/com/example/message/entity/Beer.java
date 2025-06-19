package com.example.message.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "beers")
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "beer_name")
    private String beerName;

    private Integer price;

    @Column(name = "jan_code", unique = true)
    private Long janCode;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // --- コンストラクタ ---
    public Beer() {
    }

    public Beer(String beerName, Integer price, Long janCode) {
        this.beerName = beerName;
        this.price = price;
        this.janCode = janCode;
        this.isDeleted = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // --- Getter ---
    public Integer getId() {
        return id;
    }

    public String getBeerName() {
        return beerName;
    }

    public Integer getPrice() {
        return price;
    }

    public Long getJanCode() {
        return janCode;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // --- Setter ---
    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setJanCode(Long janCode) {
        this.janCode = janCode;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    public void onCreate() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
}

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}