package com.example.demo.controllers;

import com.example.demo.Models.Post;
import com.example.demo.controllers.repository.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class MainController {

    @Autowired
    private PostRepository postRepository;


    @GetMapping("/")
    public String test(Model model) {
        model.addAttribute("test");
        return "test";
    }


// worked version
    @PostMapping("/")
    public String roomAdd(@RequestParam String name, @RequestParam("country") String country,Model model, HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        System.out.println("User IP Address: " + ipAddress);


        Post post = new Post(name, country);
        postRepository.save(post);


        long id = post.getId(); // Get the id of the newly created room
        return "redirect:/room/" + id; // Redirect the user to the room page with the specified id

    }
}


//192.168.0.241:8081