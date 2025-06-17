package com.example.message.model;

import java.util.Map;
public class ForecastResult {
        private String date;
    private String dayOfWeek;
    private String weather;
    private double temperature;
    private int predictedReservations;
    private Map<String, Integer> predictedItems;

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

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getPredictedReservations() {
        return predictedReservations;
    }

    public void setPredictedReservations(int predictedReservations) {
        this.predictedReservations = predictedReservations;
    }

    public Map<String, Integer> getPredictedItems() {
        return predictedItems;
    }

    public void setPredictedItems(Map<String, Integer> predictedItems) {
        this.predictedItems = predictedItems;
    }
}
