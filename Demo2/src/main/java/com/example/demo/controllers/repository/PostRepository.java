package com.example.demo.controllers.repository;

import com.example.demo.Models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Long> {


}
