package com.example.message.service;

import com.example.message.model.ForecastResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@Service
public class ForecastService {
    
        // PythonのAPIのURL（必要に応じて変更）
    private static final String PYTHON_API_URL = "http://localhost:5000/predict";

    public ForecastResult fetchForecast() {
        RestTemplate restTemplate = new RestTemplate();

        // 空または簡単なPOSTデータ（例として空のMapをPOST）
        Map<String, Object> requestBody = new HashMap<>();

        // ヘッダー設定（JSONを送るの伝える）
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Pythonからのレスポンスを ForecastResult にマッピングして取得
        ResponseEntity<ForecastResult> response = restTemplate.postForEntity(
                PYTHON_API_URL,
                requestEntity,
                ForecastResult.class
        );

        return response.getBody();
    }
}
