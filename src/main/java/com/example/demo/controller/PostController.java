package com.example.demo.controller;


import com.example.demo.entity.Post;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.PostsService;

import java.util.List;

@RestController
public class PostController {

    /**
     * Show how to use RequestMapping for return all elements
     *
     * @return all Posts tha have the Array
     */
    @RequestMapping("/posts")
    public List<Post> getPosts(){
        return new PostsService().getPosts();
    }

    /**
     * Show how to use RequestMapping for return just an element
     *
     * @param id identifier of element in the Array
     * @return the Post Object that is equal to the id, if don´t find the id return message
     */
    @RequestMapping("/posts/{id}")
    public Post getPostById(@PathVariable int id){
        return new PostsService().getPost(id);
    }

    /**
     * Show how to update a data of Post, the input is a json that represent the Post Object
     *
     * @param listElement json that represent the Post Object tht would be added, then return a message.
     */
    @PostMapping("/posts")
    public void addPost(@RequestBody Post listElement){
        new PostsService().addPost(listElement);
    }

    /**
     * Show how to update data of Post, use the id to update that Post
     *
     * @param post json to represente the Post Object to be updated
     * @param id identifier of the elemente to be updated. If don't found the is return message
     */
    @PutMapping("/posts/{id}")
    public void updatePost(@RequestBody Post post,@PathVariable int id){
        new PostsService().updatePost(post, id);
    }

    /**
     * Show how to delete a Post
     * @param id identifier of post that would be deleted. If don't found the is return message
     */
    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable int id){
        new PostsService().deletePost(id);
    }

}
