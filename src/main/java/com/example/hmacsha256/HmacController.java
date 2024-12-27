package com.example.hmacsha256;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HmacController {

    @Autowired
    private HmacService hmacService;

    @PostMapping("/generate-hmac")///generate-hmac becomes "/api/genate-hmac"
    public ResponseEntity<String> generateHmac(@RequestBody HmacRequest request) {
        String hmac = hmacService.generateHmac(request.getMessage(), request.getSecretKey());
        return ResponseEntity.ok(hmac);
    }

    @PostMapping("/validate-hmac")
    public ResponseEntity<Boolean> validateHmac(@RequestBody HmacValidationRequest request) {
        boolean isValid = hmacService.validateHmac(request.getMessage(), request.getSecretKey(), request.getHmac());
        return ResponseEntity.ok(isValid);
    }
}

