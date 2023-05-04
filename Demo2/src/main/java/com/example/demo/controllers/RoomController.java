package com.example.demo.controllers;

import com.example.demo.Models.Post;

import com.example.demo.controllers.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@Controller
public class RoomController {

    // Автоматическое связывание объекта PostRepository для доступа к данным сущности Post
    @Autowired
    private PostRepository postRepository;

    // Метод, который обрабатывает GET-запрос на путь "/rooms" и возвращает шаблон "rooms"
    @GetMapping("/rooms")
    public String rooms(Model model){
        // Получение всех записей из репозитория postRepository
        Iterable<Post> posts = postRepository.findAll();
        // Добавление списка записей в модель
        model.addAttribute("posts", posts);
        // Возврат имени шаблона для отображения списка комнат
        return "rooms";
    }

    // Метод, который обрабатывает GET-запрос на путь "/room/{id}" и возвращает шаблон "room"
    @GetMapping("/room/{id}")
    public String joinRoom(@PathVariable("id") long id, Model model){
        // Проверка существования записи с указанным id в репозитории postRepository
        if(!postRepository.existsById(id)){
            return "redirect:/";
        }
        // Получение записи из репозитория postRepository по указанному id
        Optional<Post> post = postRepository.findById(id);
        // Создание нового списка res и добавление полученной записи в него, если она существует
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        // Добавление списка записей в модель
        model.addAttribute("post", res);
        // Возврат имени шаблона для отображения комнаты
        return "room";
    }
}









