package com.example.message.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.message.entity.WeatherData;
import java.util.Optional;
import java.util.List;


@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Integer> {
    Optional<WeatherData> findByObservationDate(LocalDate date);
    List<WeatherData> findByObservationDateIn(List<LocalDate> dates);

}

