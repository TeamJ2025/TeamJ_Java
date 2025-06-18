package com.example.message.service;

import com.example.message.model.CsvForecastRecord;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvForecastService {

    public List<CsvForecastRecord> loadCsvForecast() {
        List<CsvForecastRecord> records = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        getClass().getResourceAsStream("/data/beers.csv"), StandardCharsets.UTF_8))) {

            String line;
            boolean isFirst = true;

            while ((line = reader.readLine()) != null) {
                if (isFirst) {
                    isFirst = false; // ヘッダー行スキップ
                    continue;
                }

                String[] cols = line.split(",");

                CsvForecastRecord record = new CsvForecastRecord();
                record.setDate(cols[0]);
                record.setDayOfWeek(cols[1]);
                record.setVisitors(Integer.parseInt(cols[2]));
                record.setTotalCups(Integer.parseInt(cols[3]));
                record.setTotalSalesYen(Integer.parseInt(cols[4]));
                record.setPaleAleBottles(Integer.parseInt(cols[5]));
                record.setLagerBottles(Integer.parseInt(cols[7]));
                record.setIpaBottles(Integer.parseInt(cols[9]));
                record.setWhiteBeerBottles(Integer.parseInt(cols[11]));
                record.setBlackBeerBottles(Integer.parseInt(cols[13]));
                record.setFruitBeerBottles(Integer.parseInt(cols[15]));

                records.add(record);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return records;
    }
}
