package com.example.message.service;

import com.example.message.model.ForecastResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Value;

@Service
public class ForecastService {

    @Value("${forecast.api.url}")
    private String apiUrl;

    @Value("${forecast.api.key}")
    private String apiKey;

    public List<Map<String, Object>> fetchForecast() {
        try {
            String fullUrl = apiUrl + "?code=" + apiKey;
            URL url = new URL(fullUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            conn.getOutputStream().write("{}".getBytes());

            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder json = new StringBuilder();
            while (scanner.hasNext()) {
                json.append(scanner.nextLine());
            }

            ObjectMapper mapper = new ObjectMapper();
            System.out.println("Received JSON:");
            System.out.println(json.toString());
            
            return mapper.readValue(json.toString(), new TypeReference<List<Map<String, Object>>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
