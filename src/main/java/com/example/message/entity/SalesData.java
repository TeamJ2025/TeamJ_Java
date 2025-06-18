//Beer.java および Sales.java を作成したためコメントアウト

/*package com.example.message.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sales_date")
public class SalesData {

    // @ManyToOne
    // @JoinColumn(name = "user_id")
    // private User user;

    @Id
    @Column(name = "sale_day")
    private LocalDate saleDay;

    @Column(name = "total_sales_yen")
    private Integer totalSalesYen;

    @Column(name = "total_cups")
    private Integer totalCups;

    @Column(name = "pale_ale_bottles")
    private Integer paleAleBottles;

    @Column(name = "pale_ale_yen")
    private Integer paleAleYen;

    @Column(name = "lager_bottles")
    private Integer lagerBottles;

    @Column(name = "lager_yen")
    private Integer lagerYen;

    @Column(name = "ipa_bottles")
    private Integer ipaBottles;

    @Column(name = "ipa_yen")
    private Integer ipaYen;

    @Column(name = "white_beer_bottles")
    private Integer whiteBeerBottles;

    @Column(name = "white_beer_yen")
    private Integer whiteBeerYen;

    @Column(name = "black_beer_bottles")
    private Integer blackBeerBottles;

    @Column(name = "black_beer_yen")
    private Integer blackBeerYen;

    @Column(name = "fruit_beer_bottles")
    private Integer fruitBeerBottles;

    @Column(name = "fruit_beer_yen")
    private Integer fruitBeerYen;

    @Column(name = "reservation_count")
    private Integer reservationCount;

    @Column(name = "reservation_people")
    private Integer reservationPeople;

    @Column(name = "visitors")
    private Integer visitors;

    // ====== Getter and Setter ======

    // public class User {  //主キー
    //     private String name;
    //     private String email;
    // }

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

    public Integer getPaleAleBottles() {
        return paleAleBottles;
    }

    public void setPaleAleBottles(Integer paleAleBottles) {
        this.paleAleBottles = paleAleBottles;
    }

    public Integer getPaleAleYen() {
        return paleAleYen;
    }

    public void setPaleAleYen(Integer paleAleYen) {
        this.paleAleYen = paleAleYen;
    }

    public Integer getLagerBottles() {
        return lagerBottles;
    }

    public void setLagerBottles(Integer lagerBottles) {
        this.lagerBottles = lagerBottles;
    }

    public Integer getLagerYen() {
        return lagerYen;
    }

    public void setLagerYen(Integer lagerYen) {
        this.lagerYen = lagerYen;
    }

    public Integer getIpaBottles() {
        return ipaBottles;
    }

    public void setIpaBottles(Integer ipaBottles) {
        this.ipaBottles = ipaBottles;
    }

    public Integer getIpaYen() {
        return ipaYen;
    }

    public void setIpaYen(Integer ipaYen) {
        this.ipaYen = ipaYen;
    }

    public Integer getWhiteBeerBottles() {
        return whiteBeerBottles;
    }

    public void setWhiteBeerBottles(Integer whiteBeerBottles) {
        this.whiteBeerBottles = whiteBeerBottles;
    }

    public Integer getWhiteBeerYen() {
        return whiteBeerYen;
    }

    public void setWhiteBeerYen(Integer whiteBeerYen) {
        this.whiteBeerYen = whiteBeerYen;
    }

    public Integer getBlackBeerBottles() {
        return blackBeerBottles;
    }

    public void setBlackBeerBottles(Integer blackBeerBottles) {
        this.blackBeerBottles = blackBeerBottles;
    }

    public Integer getBlackBeerYen() {
        return blackBeerYen;
    }

    public void setBlackBeerYen(Integer blackBeerYen) {
        this.blackBeerYen = blackBeerYen;
    }

    public Integer getFruitBeerBottles() {
        return fruitBeerBottles;
    }

    public void setFruitBeerBottles(Integer fruitBeerBottles) {
        this.fruitBeerBottles = fruitBeerBottles;
    }

    public Integer getFruitBeerYen() {
        return fruitBeerYen;
    }

    public void setFruitBeerYen(Integer fruitBeerYen) {
        this.fruitBeerYen = fruitBeerYen;
    }

    public Integer getReservationCount() {
        return reservationCount;
    }

    public void setReservationCount(Integer reservationCount) {
        this.reservationCount = reservationCount;
    }

    public Integer getReservationPeople() {
        return reservationPeople;
    }

    public void setReservationPeople(Integer reservationPeople) {
        this.reservationPeople = reservationPeople;
    }

    public Integer getVisitors() {
        return visitors;
    }

    public void setVisitors(Integer visitors) {
        this.visitors = visitors;
    }
}
*/