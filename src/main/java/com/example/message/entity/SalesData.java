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
    private int totalSalesYen;

    @Column(name = "total_cups")
    private int totalCups;

    @Column(name = "pale_ale_bottles")
    private int paleAleBottles;

    @Column(name = "pale_ale_yen")
    private int paleAleYen;

    @Column(name = "lager_bottles")
    private int lagerBottles;

    @Column(name = "lager_yen")
    private int lagerYen;

    @Column(name = "ipa_bottles")
    private int ipaBottles;

    @Column(name = "ipa_yen")
    private int ipaYen;

    @Column(name = "white_beer_bottles")
    private int whiteBeerBottles;

    @Column(name = "white_beer_yen")
    private int whiteBeerYen;

    @Column(name = "black_beer_bottles")
    private int blackBeerBottles;

    @Column(name = "black_beer_yen")
    private int blackBeerYen;

    @Column(name = "fruit_beer_bottles")
    private int fruitBeerBottles;

    @Column(name = "fruit_beer_yen")
    private int fruitBeerYen;

    @Column(name = "reservation_count")
    private int reservationCount;

    @Column(name = "reservation_people")
    private int reservationPeople;

    @Column(name = "visitors")
    private int visitors;

    // ====== Getter and Setter ======

    public LocalDate getSaleDay() {
        return saleDay;
    }

    public void setSaleDay(LocalDate saleDay) {
        this.saleDay = saleDay;
    }

    public int getTotalSalesYen() {
        return totalSalesYen;
    }

    public void setTotalSalesYen(int totalSalesYen) {
        this.totalSalesYen = totalSalesYen;
    }

    public int getTotalCups() {
        return totalCups;
    }

    public void setTotalCups(int totalCups) {
        this.totalCups = totalCups;
    }

    public int getPaleAleBottles() {
        return paleAleBottles;
    }

    public void setPaleAleBottles(int paleAleBottles) {
        this.paleAleBottles = paleAleBottles;
    }

    public int getPaleAleYen() {
        return paleAleYen;
    }

    public void setPaleAleYen(int paleAleYen) {
        this.paleAleYen = paleAleYen;
    }

    public int getLagerBottles() {
        return lagerBottles;
    }

    public void setLagerBottles(int lagerBottles) {
        this.lagerBottles = lagerBottles;
    }

    public int getLagerYen() {
        return lagerYen;
    }

    public void setLagerYen(int lagerYen) {
        this.lagerYen = lagerYen;
    }

    public int getIpaBottles() {
        return ipaBottles;
    }

    public void setIpaBottles(int ipaBottles) {
        this.ipaBottles = ipaBottles;
    }

    public int getIpaYen() {
        return ipaYen;
    }

    public void setIpaYen(int ipaYen) {
        this.ipaYen = ipaYen;
    }

    public int getWhiteBeerBottles() {
        return whiteBeerBottles;
    }

    public void setWhiteBeerBottles(int whiteBeerBottles) {
        this.whiteBeerBottles = whiteBeerBottles;
    }

    public int getWhiteBeerYen() {
        return whiteBeerYen;
    }

    public void setWhiteBeerYen(int whiteBeerYen) {
        this.whiteBeerYen = whiteBeerYen;
    }

    public int getBlackBeerBottles() {
        return blackBeerBottles;
    }

    public void setBlackBeerBottles(int blackBeerBottles) {
        this.blackBeerBottles = blackBeerBottles;
    }

    public int getBlackBeerYen() {
        return blackBeerYen;
    }

    public void setBlackBeerYen(int blackBeerYen) {
        this.blackBeerYen = blackBeerYen;
    }

    public int getFruitBeerBottles() {
        return fruitBeerBottles;
    }

    public void setFruitBeerBottles(int fruitBeerBottles) {
        this.fruitBeerBottles = fruitBeerBottles;
    }

    public int getFruitBeerYen() {
        return fruitBeerYen;
    }

    public void setFruitBeerYen(int fruitBeerYen) {
        this.fruitBeerYen = fruitBeerYen;
    }

    public int getReservationCount() {
        return reservationCount;
    }

    public void setReservationCount(int reservationCount) {
        this.reservationCount = reservationCount;
    }

    public int getReservationPeople() {
        return reservationPeople;
    }

    public void setReservationPeople(int reservationPeople) {
        this.reservationPeople = reservationPeople;
    }

    public int getVisitors() {
        return visitors;
    }

    public void setVisitors(int visitors) {
        this.visitors = visitors;
    }
}
