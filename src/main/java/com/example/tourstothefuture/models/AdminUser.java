package com.example.tourstothefuture.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity //пишемо сутності(об'єкти) у БД
@DiscriminatorValue("1") //дискримінатор, для ідентифікації Адмінів (для відокремлення від інших користувачів у таблицу супер-класу)
public class AdminUser extends User { //сутність Адмін, послідовник класу User (таблиця буде мати аналогічну назву)

    public static final int DISCRIMINATOR_VALUE = 1; //робимо дискримінатор константою, щоб не задвоювалися дані та для
    //лояльності роботи enum з іншими класами
    public AdminUser() { } //обов'язковий конструктор для Sping-ових сутностей (без аргументів)

    public AdminUser(String name, String email, String password) { // конструктор класу
        super(name, email, password); //реалізація(ініціалізація) від класу-батька
    }
}
