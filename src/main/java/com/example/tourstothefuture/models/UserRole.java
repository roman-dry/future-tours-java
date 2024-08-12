package com.example.tourstothefuture.models;

public enum UserRole {
    CUSTOMER(User.DISCRIMINATOR_VALUE),
    ADMIN(AdminUser.DISCRIMINATOR_VALUE);

    private final int discriminatorValue; //кожна роль має своє значення discriminatorValue, яке використовується для відокремлення отличия
    //типів юзерів при наслідуванні і збереженні в БД
    UserRole(int discriminatorValue) { //конструктор класса с параметром дискриминатор
        this.discriminatorValue = discriminatorValue; //ініціалізація дискримінатору
    }

    public int getDiscriminatorValue() { //геттер для отримання значення дискримінатора за границями класу
        return discriminatorValue;
    }

    @Override
    public String toString() { //перевизначаємо метод к строковому вигляду, який ми хочемо бачити
        return String.format("ROLE_%s", name()); //будемо отримувати роль + ім'я enum'a
    }
}
