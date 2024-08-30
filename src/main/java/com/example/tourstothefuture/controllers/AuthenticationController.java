package com.example.tourstothefuture.controllers;

import com.example.tourstothefuture.models.UserRole;
import com.example.tourstothefuture.requests.LoginRequest;
import com.example.tourstothefuture.requests.RegisterRequest;
import com.example.tourstothefuture.responses.AuthenticationResponse;
import com.example.tourstothefuture.servises.AuthenticationService;
import com.example.tourstothefuture.servises.RecaptchaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService service;
    private final LogoutHandler logoutHandler;
    private final RecaptchaService recaptchaService;

    public AuthenticationController(AuthenticationService service, LogoutHandler logoutHandler, RecaptchaService recaptchaService) {
        this.service = service;
        this.logoutHandler = logoutHandler;
        this.recaptchaService = recaptchaService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam UserRole role,
            @RequestParam String adminKey,
            @RequestParam(value = "g-recaptcha-response", required = false) String captchaResponse
    ) {

        /*if (!recaptchaService.verifyRecaptcha(request.getRecaptchaToken())) {
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setAccessToken("Invalid reCAPTCHA");
            authenticationResponse.setUser(null);
            return ResponseEntity.badRequest().body(authenticationResponse);
        }*/
        if (!recaptchaService.verifyRecaptcha(captchaResponse)) {
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setAccessToken("Invalid reCAPTCHA");
            authenticationResponse.setUser(null);
            return ResponseEntity.badRequest().body(authenticationResponse);
        }

        RegisterRequest registerRequest = new RegisterRequest(name, email, password, role, adminKey, captchaResponse);
        return ResponseEntity.ok(service.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestParam String email,
            @RequestParam String password
    ) {
        LoginRequest loginRequest = new LoginRequest(email, password);
        return ResponseEntity.ok(service.login(loginRequest));
    }

    @PostMapping("/logout")
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        logoutHandler.logout(request,response,authentication);

    }
}
