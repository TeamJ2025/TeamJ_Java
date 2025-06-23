package com.example.message.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "weather_data")
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sale_day", nullable = false)
    private LocalDate observationDate;

    @Column(name = "weather_code", nullable = false)
    private String weatherDescription;

    // --- コンストラクタ ---

    public WeatherData() {
    }

    public WeatherData(LocalDate observationDate, String weatherDescription) {
        this.observationDate = observationDate;
        this.weatherDescription = weatherDescription;
    }

    // --- Getter ---

    public Integer getId() {
        return id;
    }

    public LocalDate getObservationDate() {
        return observationDate;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    // --- Setter ---

    public void setId(Integer id) {
        this.id = id;
    }

    public void setObservationDate(LocalDate observationDate) {
        this.observationDate = observationDate;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }
}
