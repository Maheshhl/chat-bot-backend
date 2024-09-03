package com.acuver.controller;

import com.acuver.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatbot")
public class ChatBotController {

    @Autowired
    private OpenAIService openAIService;

    @PostMapping
    public String chat(@RequestBody String message) {
        return openAIService.getChatResponse(message);
    }
}

