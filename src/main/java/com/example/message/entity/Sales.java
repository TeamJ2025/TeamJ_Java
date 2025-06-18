package com.example.message.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.message.model.Message;  // Message クラスをimport
import jakarta.persistence.*;

@Entity
@Table(name = "sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sales_date")
    private LocalDate salesDate;

    @Column(name = "users_id")
    private Integer usersId;

    @Column(name = "beers_id")
    private Integer beersId;

    @Column(name = "sold_bottles")
    private Integer soldBottles;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // --- 外部キー関係（リレーション） ---
    @ManyToOne
    @JoinColumn(name = "users_id", insertable = false, updatable = false)
    private Message user;

    @ManyToOne
    @JoinColumn(name = "beers_id", insertable = false, updatable = false)
    private Beer beer;

    // --- コンストラクタ ---
    public Sales() {
    }

    public Sales(LocalDate salesDate, Integer usersId, Integer beersId, Integer soldBottles) {
        this.salesDate = salesDate;
        this.usersId = usersId;
        this.beersId = beersId;
        this.soldBottles = soldBottles;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // --- Getter ---
    public Integer getId() {
        return id;
    }

    public LocalDate getSalesDate() {
        return salesDate;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public Integer getBeersId() {
        return beersId;
    }

    public Integer getSoldBottles() {
        return soldBottles;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Message getUser() {
        return user;
    }

    public Beer getBeer() {
        return beer;
    }

    // --- Setter ---
    public void setSalesDate(LocalDate salesDate) {
        this.salesDate = salesDate;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public void setBeersId(Integer beersId) {
        this.beersId = beersId;
    }

    public void setSoldBottles(Integer soldBottles) {
        this.soldBottles = soldBottles;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setUser(Message user) {
        this.user = user;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }
}