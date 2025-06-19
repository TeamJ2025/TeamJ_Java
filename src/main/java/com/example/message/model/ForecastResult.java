package com.example.message.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class ForecastResult {

    private String date;

    @JsonProperty("day_of_week")
    private String dayOfWeek;

    private String weather;

    @JsonProperty("temperature_2m_mean (°C)")
    private double temperature;

    @JsonProperty("predicted_reservations")
    private int reservationCount;

    @JsonProperty("prediction")
    private Map<String, Integer> predictedItems;

    private String iconCode;

    // --- Getter & Setter ---
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public String getWeather() { return weather; }
    public void setWeather(String weather) { this.weather = weather; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public int getReservationCount() { return reservationCount; }
    public void setReservationCount(int reservationCount) { this.reservationCount = reservationCount; }

    public Map<String, Integer> getPredictedItems() { return predictedItems; }
    public void setPredictedItems(Map<String, Integer> predictedItems) { this.predictedItems = predictedItems; }

    public String getIconCode() { return iconCode; }
    public void setIconCode(String iconCode) { this.iconCode = iconCode;}
}
