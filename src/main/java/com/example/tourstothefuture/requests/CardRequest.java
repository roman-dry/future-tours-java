package com.example.tourstothefuture.requests;

import com.example.tourstothefuture.models.CardDescription;

import java.util.List;

public class CardRequest {
    private String name;
    private List<CardDescription> descriptions;;
    private Integer price;

    public CardRequest() {
    }

    public CardRequest(String name, List<CardDescription> descriptions, Integer price) {
        this.name = name;
        this.descriptions = descriptions;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CardDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<CardDescription> descriptions) {
        this.descriptions = descriptions;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
