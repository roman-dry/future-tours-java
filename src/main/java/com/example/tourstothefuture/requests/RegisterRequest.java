package com.example.tourstothefuture.requests;

import com.example.tourstothefuture.models.UserRole;

public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private String adminKey; // додано поле для ключа адміністратора
    private String recaptchaToken;

    public RegisterRequest(String name, String email, String password, UserRole role, String adminKey, String recaptchaToken) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.adminKey = adminKey;
        this.recaptchaToken = recaptchaToken;
    }

    public RegisterRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getAdminKey() {
        return adminKey;
    }

    public void setAdminKey(String adminKey) {
        this.adminKey = adminKey;
    }

    public String getRecaptchaToken() {
        return recaptchaToken;
    }

    public void setRecaptchaToken(String recaptchaToken) {
        this.recaptchaToken = recaptchaToken;
    }
}
