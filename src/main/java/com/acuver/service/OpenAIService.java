package com.acuver.service;

import com.acuver.config.OpenAIConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@Service
public class OpenAIService {

    @Autowired
    private OpenAIConfig openAIConfig;

    @Autowired
    private RestTemplate restTemplate;

    public String getChatResponse(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + openAIConfig.getApiKey());
        headers.set("Content-Type", "application/json");

        JSONObject request = new JSONObject();
        request.put("model", "gpt-3.5-turbo");
        request.put("prompt", prompt);
        request.put("max_tokens", 150);

        HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);

        ResponseEntity<String> response = restTemplate.exchange(
                openAIConfig.getApiUrl(),
                HttpMethod.POST,
                entity,
                String.class
        );

        JSONObject jsonResponse = new JSONObject(response.getBody());
        return jsonResponse.getJSONArray("choices").getJSONObject(0).getString("text");
    }
}
