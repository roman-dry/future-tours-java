package com.example.tourstothefuture.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "card") //ім'я для таблиці в БД
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Card {
    @Id //дане поле виступає ідентифікатором
    @GeneratedValue(strategy = GenerationType.IDENTITY) //автогенерація id-шніка
    private Integer id; //поле - стовбець таблиці з ім'ям id
    private String name;

    // Використовуємо @ElementCollection для збереження списку в базі даних
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CardDescription> descriptions = new ArrayList<>();
    private Integer price;

    public Card() {
    }

    public Card(String name, List<CardDescription> descriptions, Integer price) {
        this.name = name;
        this.descriptions = descriptions;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + descriptions + '\'' +
                ", price=" + price +
                '}';
    }
}
