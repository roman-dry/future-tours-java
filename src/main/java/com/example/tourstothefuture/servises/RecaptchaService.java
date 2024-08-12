package com.example.tourstothefuture.servises;

import com.example.tourstothefuture.dto.ReCaptchaResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
@Service
public class RecaptchaService {
    @Value("${recaptcha.secret}")
    private String recaptchaSecret;

    private static final String RECAPTCHA_VERIFY_URL =
            "https://www.google.com/recaptcha/api/siteverify";

    /*public boolean verifyRecaptcha(String recaptchaToken) {
        System.out.println(recaptchaToken);
        System.out.println(recaptchaSecret);
        if (recaptchaToken == null || recaptchaToken.isEmpty()) {
            // Якщо токен null або порожній, можна повернути false або кинути виняток
            return false;
        }

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> body = new HashMap<>();
        body.put("secret", recaptchaSecret);
        body.put("response", recaptchaToken);

        Map<String, Object> response =
                restTemplate.postForObject(RECAPTCHA_VERIFY_URL, body, Map.class);
        System.out.println(response);


        if (response == null || response.get("success") == null) {
            return false;
        }

        return (Boolean) response.get("success");
    }*/

    public boolean verifyRecaptcha(String recaptchaToken) {
        if (recaptchaToken == null || recaptchaToken.isEmpty()) {
            return false;
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s?secret=%s&response=%s", RECAPTCHA_VERIFY_URL, recaptchaSecret, recaptchaToken);

        Map<String, Object> response = restTemplate.postForObject(url, null, Map.class);
        System.out.println(response);

        if (response == null || !((Boolean) response.get("success"))) {
            if (response != null && response.get("error-codes") != null) {
                System.out.println("reCAPTCHA verification failed: " + response.get("error-codes"));
            }
            return false;
        }

        return true;
    }
}
