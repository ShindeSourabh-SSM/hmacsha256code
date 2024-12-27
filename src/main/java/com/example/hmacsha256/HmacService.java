package com.example.hmacsha256;

import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class HmacService {

    private static final String HMAC_SHA256 = "HmacSHA256";

    public String generateHmac(String message, String secretKey) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), HMAC_SHA256);
            Mac mac = Mac.getInstance(HMAC_SHA256);
            mac.init(keySpec);
            byte[] hmacBytes = mac.doFinal(message.getBytes());
            return Base64.getEncoder().encodeToString(hmacBytes);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate HMAC", e);
        }
    }

    public boolean validateHmac(String message, String secretKey, String providedHmac) {
        String generatedHmac = generateHmac(message, secretKey);
        return generatedHmac.equals(providedHmac);
    }
}
