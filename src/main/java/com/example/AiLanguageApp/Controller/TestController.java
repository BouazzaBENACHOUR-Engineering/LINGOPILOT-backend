package com.example.AiLanguageApp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test/protected")
    public String protectedEndpoint() {
        return "JWT authentication successful - protected endpoint reached";
    }
}