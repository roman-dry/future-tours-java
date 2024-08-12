package com.example.tourstothefuture.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserFactory {
    private static final Logger LOGGER = LogManager.getLogger(UserFactory.class);

    public static User createUser(
            UserRole userRole, String name, String email, String password) { //метод, який
        //буде добавляти юзерів і його класів-послідовників AdminUser
        User result = null; //змінна-посилання на об'єкт типу класу User, ініціалізуємо null

        LOGGER.info("User role: " + userRole); //виводимо роль юзера

        switch (userRole) { //за допомогою оператора switch перевіряємо значення ролі юзера
            case CUSTOMER -> result = new User(name, email, password); //якщо роль STUDENT - то створюємо новий об'єкт з
            //поведінкою класу User з відповідними параметрами
            case ADMIN -> result = new AdminUser(name, email, password); //якщо роль ADMIN - то створюємо новий об'єкт з
            //поведінкою класу AdminUser з відповідними параметрами

        }

        return result; //повертаємо посилання на новий об'єкт
    }
}
