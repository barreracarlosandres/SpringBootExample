package com.example.demo.controller;


import com.example.demo.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.PostsService;

import java.util.List;

@RestController
public class PostController {


    PostsService postsService;

    /**
     * Dependency Injection
     * Nota: It´s not necessary use the @Autowared, I use just for explicit it
     */
    @Autowired
    public PostController(PostsService postsService) {
        this.postsService = postsService;
    }

    /**
     * Show how to use RequestMapping for return all elements
     *
     * @return all Posts tha have the Array
     */
    @RequestMapping("/posts")
    public ResponseEntity<List<Post>> getPosts() {
        return new ResponseEntity<>(postsService.getPosts(), HttpStatus.OK);
    }

    /**
     * Show how to use RequestMapping for return just an element
     *
     * @param id identifier of element in the Array
     * @return the Post Object that is equal to the id, if don´t find the id return message
     */
    @RequestMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable int id) {
        return new ResponseEntity<>(postsService.getPostById(id), HttpStatus.OK);
    }

    /**
     * Show how to update a data of Post, the input is a json that represent the Post Object
     *
     * @param listElement json that represent the Post Object tht would be added, then return a message.
     */
    @PostMapping("/posts")
    public ResponseEntity addPost(@RequestBody final Post listElement) {
        if(postsService.addPost(listElement)){
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_GATEWAY);
        }
    }

    /**
     * Show how to update data of Post, use the id to update that Post
     *
     * @param post json to represente the Post Object to be updated
     * @param id   identifier of the elemente to be updated. If don't found the is return message
     */
    @PutMapping("/posts/{id}")
    public ResponseEntity updatePost(@RequestBody final Post post, @PathVariable final int id) {
        if(postsService.updatePostById(post, id)){
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Show how to delete a Post
     *
     * @param id identifier of post that would be deleted. If don't found the is return message
     */
    @DeleteMapping("/posts/{id}")
    public ResponseEntity deletePost(@PathVariable final int id) {
        if(postsService.deletePostById(id)){
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /*@PostConstruct
    public void saludar(){
        System.out.println("Hola");
    }

    @PreDestroy
    public void despedirse(){
        System.out.println("Adios");
    }*/

}
