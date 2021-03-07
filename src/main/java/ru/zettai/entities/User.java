package ru.zettai.entities;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "my_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя пользователя не должно быть пустым!")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Фамилия пользователя не должна быть пустой!")
    @Column(name = "surname")
    private String surName;

    @Min(value = 1, message = "Вам не может быть 0 или меньше лет")
    @Max(value = 150, message = "Если Вам больше 150, то вы вероятно горец или не человек.")
    @Column(name = "age")
    private int age;

    @NotBlank(message = "Поле должно содержать значение!")
    @Email(message = "Некорректный ввод почты")
    @Column(name = "email")
    private String email;

    public User() {
    }

    public User(String name, String surname, int age, String email) {
        this.name = name;
        this.surName = surname;
        this.age = age;
        this.email = email;
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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surname) {
        this.surName = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "==============================\n" +
                "User#" + id + ":\n" +
                "name: " + name + " surName: " + surName + "\n" +
                "age: " + age +
                "\nemail:" + email + '\n'+
                "==============================\n";
    }
}
