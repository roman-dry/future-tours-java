package com.example.tourstothefuture.servises;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
@Service
public class RecaptchaService {
    /*@Value("${recaptcha.secret}")
    private String recaptchaSecret;*/
    private final String recaptchaSecretKey;

    public RecaptchaService() {
        Dotenv dotenv = Dotenv.configure().load();
        this.recaptchaSecretKey = dotenv.get("RECAPTCHA_SECRET_KEY");
    }

    private static final String RECAPTCHA_VERIFY_URL =
            "https://www.google.com/recaptcha/api/siteverify";


    /*public boolean verifyRecaptcha(String recaptchaToken) {
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
    }*/

    public boolean verifyRecaptcha(String recaptchaToken) {
        System.out.println(recaptchaSecretKey);
        if (recaptchaToken == null || recaptchaToken.isEmpty()) {
            return false;
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s?secret=%s&response=%s", RECAPTCHA_VERIFY_URL, recaptchaSecretKey, recaptchaToken);

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
