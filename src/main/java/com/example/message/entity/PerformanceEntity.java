package com.example.message.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sales_date")
@Getter
@Setter
public class PerformanceEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // private int reservation_count;
    // private int reservation_people;
    // private int visitors;
    
    // @Column(name = "totalCups")
    // private int total_cups;
    // @Column(name = "totalSalesYen")
    // private int total_sales_yen;

    // @Column(name = "pale_ale_bottles")
    // private int paleAleBottles;
    // private int pale_ale_yen;
    // @Column(name = "lager_bottles")
    // private int lagerBottles;
    // private int lager_yen;
    // @Column(name = "ipa_bottles")
    // private int ipaBottles;
    // private int ipa_yen;
    // @Column(name = "white_beer_bottles")
    // private int whiteBeerBottles;
    // private int white_beer_yen;
    // @Column(name = "black_beer_bottles")
    // private int blackBeerBottles;
    // private int black_beer_yen;
    // @Column(name = "fruit_beer_bottles")
    // private int fruitBeerBottles;
    // private int fruit_beer_yen;
}
