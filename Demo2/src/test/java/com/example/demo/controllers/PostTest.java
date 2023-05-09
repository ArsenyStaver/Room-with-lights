package com.example.demo.controllers;

import com.example.demo.Models.Post;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@WebMvcTest
class PostTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getNameAndCountries() {
        Post post = new Post("room", "Russia");
        assertEquals("room", post.getRoomNames());
        assertEquals("Russia", post.getCountries());
    }

    @Test
    void setNameAndCounties() {
        Post post = new Post("room", "Russia");
        post.setRoomNames("room3");
        post.setCountries("Poland");
        assertEquals("room3", post.getRoomNames());
        assertEquals("Poland", post.getCountries());
    }

}