package com.example.demo.controllers;

import com.example.demo.Models.Post;
import com.example.demo.controllers.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



import java.util.ArrayList;
import java.util.Optional;


@Controller
public class RoomController {

    @Autowired
    private PostRepository postRepository;



    @GetMapping("/rooms")
    public String rooms(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "rooms";
    }


    @GetMapping("/room/{id}")
    public String joinRoom(@PathVariable("id") long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "room";
    }


}










