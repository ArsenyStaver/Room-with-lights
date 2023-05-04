package com.example.demo.controllers;

import com.example.demo.Models.Post;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    @Test
    void getNameAndCountries() {
        Post post = new Post("room", "Russia");
        assertEquals("room", post.getRoomNames());
        assertEquals("Russia", post.getCountries());
    }

    @Test
    void setNameAndCounties(){
        Post post = new Post("room", "Russia");
        post.setRoomNames("room3");
        post.setCountries("Poland");
        assertEquals("room3", post.getRoomNames());
        assertEquals("Poland", post.getCountries());
    }

}