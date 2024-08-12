package com.example.tourstothefuture.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.time.LocalDateTime;

@Entity //пишемо сутності(об'єкти) у БД
@Table(name = "clients") //ім'я для таблиці в БД
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//визначаємо стратегію наслідування - усі поля класів (як супер, так і
//послідовників) відображаємо в одну таблицю
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.INTEGER)
//стовбець в таблиці з назвою і типом даних(число)
@DiscriminatorValue("0") //дискримінатор, для ідентифікації Юзера
public class User implements UserDetails, Serializable {

    public static final int DISCRIMINATOR_VALUE = 0; //робимо дискримінатор константою, щоб не задвоювалися дані та для
    //лояльності роботи enum з іншими класами
    private static final Logger LOGGER = LogManager.getLogger(User.class);

    @Id //дане поле виступає ідентифікатором
    @GeneratedValue(strategy = GenerationType.IDENTITY) //автогенерація id-шніка
    private Long id; //поле - стовбець таблиці з ім'ям id

    @Column(nullable = false) //не може бути пустим
    @NotBlank(message = "User name cannot be empty") // повідомлення
    @Pattern(regexp = "^[A-Z][a-zA-Z]*$", message = "Invalid characters in name. Only letters are allowed.")
    //перевірка - лише символи
    @Length(max = 255, message = "Message too long (limit - 255 B)")
    //встановлюємо рамки для кількості символів в імені,
    //при порушенні виводимо повідомлення
    private String name; //поле - стовбець для імені

    @Temporal(value = TemporalType.TIMESTAMP) //наголошуємо, що поле буде мати часову мітку з датою та часом
    @Column(name = "register_date", nullable = false) //ім'я для стовбця в таблиці, не може бути пустим
    private LocalDateTime registerDate; //поле - стовбець для реєстрації

    @Column(unique = true, nullable = false) //має бути унікальним, не може бути пустим
    @NotBlank(message = "Email cannot be empty") //повідомлення при пустому полі
//    @Pattern(regexp = "^[A-Za-z0-9._%+]+[A-Za-z0-9._%-]*@[A-Za-z0-9]+([\\.-][A-Za-z0-9]+)*\\.[A-Za-z]{2,}$",
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Invalid email format. Only letters, numbers, dots, and one @ symbol are allowed.")
    //лише символи та цифри
    private String email; //поле - стовбець для номера пошти

    @Column(name = "is_banned")
    private Boolean isBanned = false;

    @NotBlank(message = "Password cannot be empty")
    @JsonIgnore
    private String password;

    private Boolean isActive = true;

    public User() {
    }

    public User(String name, String email, String password) { //конструктор класу
        //ініціалізація полів класу
        this.name = name;
        this.email = email;
        this.password = password;

        this.setRegisterDate(LocalDateTime.now()); //місцевий час при реєстрації
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getBanned() {
        return isBanned;
    }

    public void setBanned(Boolean banned) {
        isBanned = banned;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(UserRole.CUSTOMER.toString()));

        if (this instanceof AdminUser) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.toString()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive = true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", RegisterDate=" + registerDate +
                ", email='" + email + '\'' +
                '}';
    }
}
