package com.example.message.model;

public class CsvForecastRecord {
    private String date;
    private String dayOfWeek;
    private int visitors;
    private int totalCups;
    private int totalSalesYen;

    private int paleAleBottles;
    private int lagerBottles;
    private int ipaBottles;
    private int whiteBeerBottles;
    private int blackBeerBottles;
    private int fruitBeerBottles;

    // --- Getter & Setter ---
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getVisitors() {
        return visitors;
    }

    public void setVisitors(int visitors) {
        this.visitors = visitors;
    }

    public int getTotalCups() {
        return totalCups;
    }

    public void setTotalCups(int totalCups) {
        this.totalCups = totalCups;
    }

    public int getTotalSalesYen() {
        return totalSalesYen;
    }

    public void setTotalSalesYen(int totalSalesYen) {
        this.totalSalesYen = totalSalesYen;
    }

    public int getPaleAleBottles() {
        return paleAleBottles;
    }

    public void setPaleAleBottles(int paleAleBottles) {
        this.paleAleBottles = paleAleBottles;
    }

    public int getLagerBottles() {
        return lagerBottles;
    }

    public void setLagerBottles(int lagerBottles) {
        this.lagerBottles = lagerBottles;
    }

    public int getIpaBottles() {
        return ipaBottles;
    }

    public void setIpaBottles(int ipaBottles) {
        this.ipaBottles = ipaBottles;
    }

    public int getWhiteBeerBottles() {
        return whiteBeerBottles;
    }

    public void setWhiteBeerBottles(int whiteBeerBottles) {
        this.whiteBeerBottles = whiteBeerBottles;
    }

    public int getBlackBeerBottles() {
        return blackBeerBottles;
    }

    public void setBlackBeerBottles(int blackBeerBottles) {
        this.blackBeerBottles = blackBeerBottles;
    }

    public int getFruitBeerBottles() {
        return fruitBeerBottles;
    }

    public void setFruitBeerBottles(int fruitBeerBottles) {
        this.fruitBeerBottles = fruitBeerBottles;
    }
}
