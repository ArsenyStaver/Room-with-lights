package com.example.demo.controllers;

import com.example.demo.Models.Post;
import com.example.demo.controllers.repository.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



// Аннотация, которая указывает, что это класс контроллера
@Controller
public class MainController {

    // Аннотация, которая указывает на то, что Spring должен автоматически внедрить зависимость PostRepository в этот класс
    @Autowired
    private PostRepository postRepository;

    // Обработчик GET запроса для страницы "/"
    @GetMapping("/")
    public String test(Model model) {
        model.addAttribute("test");
        return "test";
    }

    // Обработчик POST запроса для добавления новой записи в базу данных
    @PostMapping("/")
    // RequestParam-Аннотация, которая указывает на параметры запроса, переданные через URL
    //Model model - параметр, представляющий модель данных для передачи данных между контроллером и представлением
    //HttpServletRequest request - параметр, представляющий HTTP-запрос, который был отправлен клиентом
    public String roomAdd(@RequestParam String name, @RequestParam("country") String country, Model model, HttpServletRequest request) {
        // Получаем IP-адрес пользователя
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        System.out.println("User IP Address: " + ipAddress);

        // Создаем новую запись и сохраняем ее в базе данных
        Post post = new Post(name, country);
        postRepository.save(post);

        // Получаем ID новой записи и перенаправляем пользователя на страницу этой записи
        long id = post.getId();
        return "redirect:/room/" + id;
    }
}




