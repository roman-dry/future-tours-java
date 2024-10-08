package com.example.tourstothefuture.responses;

import com.example.tourstothefuture.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {

    private String message;
    @JsonProperty("access_token")
    private String accessToken;
    private User user;

    public AuthenticationResponse(String message, String accessToken, User user) {
        this.message = message;
        this.accessToken = accessToken;
        this.user = user;
    }

    public AuthenticationResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
