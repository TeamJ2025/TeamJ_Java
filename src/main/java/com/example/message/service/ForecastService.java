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

@Service
public class ForecastService {

    public List<Map<String, Object>> fetchForecast() {
        try {
            String urlString = "https://teamj-app.azurewebsites.net/api/http_trigger_teamJ?code=XASwEES2B5ywQ8GKr_D7uu7zUFU21IeQQ-2ry12NsxaLAzFugWiJrA==";
            URL url = new URL(urlString);
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
            return mapper.readValue(json.toString(), new TypeReference<List<Map<String, Object>>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}