package com.whiteboard.backend.controller;

import com.whiteboard.backend.model.WhiteboardData;
import com.whiteboard.backend.repository.WhiteboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*") // allow frontend requests
@RestController
@RequestMapping("/api/whiteboard")
public class WhiteboardController {

    @Autowired
    private WhiteboardRepository repository;

    @GetMapping("/health")
    public String health(){
        return "Hello";
    }

    @PostMapping("/save")
    public Map<String, String> saveWhiteboard(@RequestBody Map<String, Object> payload) {
        try {
            WhiteboardData data = new WhiteboardData();
            data.setContent(payload);
            repository.save(data);
            return Map.of("message", "Whiteboard saved successfully!");
        } catch (Exception e) {
            return Map.of("error", "Failed to save whiteboard: " + e.getMessage());
        }
    }

    @GetMapping("/load")
    public Object loadWhiteboard() {
        WhiteboardData latest = repository.findTopByOrderBySavedAtDesc();
        if (latest == null)
            return Map.of("error", "No saved whiteboard found");
        return latest.getContent();
    }
}
