package com.example.demo.Models;

import jakarta.persistence.*;

//Аннотация, которая указывает, что это класс сущности, который будет отображаться в таблице в базы данных
@Entity
public class Post {

//@Id - аннотация, которая указывает, что это поле является идентификатором записи в базе данных
//@GeneratedValue - аннотация, которая указывает, как генерировать значения для поля id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String roomNames, Countries;

    //Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getRoomNames() {
        return roomNames;
    }

    public void setRoomNames(String roomNames) {
        this.roomNames = roomNames;
    }

    public String getCountries() {
        return Countries;
    }

    public void setCountries(String countries) {
        Countries = countries;
    }

    public Post(String roomNames, String Countries) {
        this.roomNames = roomNames;
        this.Countries = Countries;


    }

    public Post() {
    }

}
