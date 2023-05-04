package com.example.demo.controllers.repository;

import com.example.demo.Models.Post;
import org.springframework.data.repository.CrudRepository;


public interface PostRepository extends CrudRepository<Post, Long> {

}
