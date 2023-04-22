package com.example.demo.controllers;

import com.example.demo.Models.Post;
import com.example.demo.controllers.repository.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;



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
    public String roomAdd(@RequestParam String name, @RequestParam("country") String country, @RequestParam(required = false) Integer state, Model model, HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        System.out.println("User IP Address: " + ipAddress);


        Post post = new Post(name, country, 0);

        postRepository.save(post);

        long id = post.getId();


        return "redirect:/room/" + id; // Redirect the user to the room page with the specified id

    }

    @PostMapping("/state")
    public ResponseEntity<String> updateState(@RequestParam String stateJS, @RequestParam Long id, HttpServletRequest request) {
        System.out.println("Received state: " + stateJS);
        int stateJSINT = Integer.parseInt(stateJS);

        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            if (post.getState() == stateJSINT) {
                System.out.println("State has not changed.");
            } else {
                post.setState(stateJSINT);
                postRepository.save(post);
                System.out.println("State updated successfully.");

            }

        } else {
            System.out.println("Post not found.");
        }

        return ResponseEntity.ok("ok");
    }


}




//192.168.0.241:8081