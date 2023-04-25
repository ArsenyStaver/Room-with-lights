package com.example.demo.Models;

import jakarta.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String roomNames, Countries;



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
