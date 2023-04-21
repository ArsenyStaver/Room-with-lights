package com.example.demo.Models;

import jakarta.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String roomNames, Countries;

    private int state;


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }




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

    public Post(String roomNames, String Countries, int state) {
        this.roomNames = roomNames;
        this.Countries = Countries;
        this.state = state;

    }

    public Post() {
    }







}
