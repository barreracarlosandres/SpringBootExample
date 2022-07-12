package com.example.demo.infrastructure.rest.controller;


import com.example.demo.application.services.ServicePost;
import com.example.demo.infrastructure.rest.dto.PostDto;
import com.example.demo.infrastructure.rest.mapper.MapperPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {


    private final ServicePost postsService;
    private final MapperPost mapperPost;

    /**
     * Dependency Injection
     * Nota: It´s not necessary use the @Autowared, I use just for explicit it
     */
    @Autowired
    public PostController(ServicePost postsService, MapperPost mapperPost) {
        this.postsService = postsService;
        this.mapperPost = mapperPost;
    }

    /**
     * Show how to use RequestMapping for return all elements
     *
     * @return all Posts tha have the Array
     */
    @RequestMapping("/posts")
    public ResponseEntity<List<PostDto>> getPosts() {
        return new ResponseEntity<>(mapperPost.toDto(postsService.getPosts()), HttpStatus.OK);
    }

    /**
     * Show how to use RequestMapping for return just an element
     *
     * @param id identifier of element in the Array
     * @return the Post Object that is equal to the id, if don´t find the id return message
     */
    @RequestMapping("/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable int id) {
        return new ResponseEntity<>(mapperPost.toDto(postsService.getPostById(id)), HttpStatus.OK);
    }

    /**
     * Show how to update a data of Post, the input is a json that represent the Post Object
     *
     * @param postDto json that represent the Post Object tht would be added, then return a message.
     */
    @PostMapping("/posts")
    public ResponseEntity<HttpStatus> addPost(@RequestBody final PostDto postDto) {
        postsService.addPost(mapperPost.toDomain(postDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Show how to update data of Post, use the id to update that Post
     *
     * @param postDto json to represente the Post Object to be updated
     * @param id      identifier of the elemente to be updated. If don't found the is return message
     */
    @PutMapping("/posts/{id}")
    public ResponseEntity<HttpStatus> updatePost(@RequestBody final PostDto postDto, @PathVariable final int id) {
        postsService.updatePostById(mapperPost.toDomain(postDto), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Show how to delete a Post
     *
     * @param id identifier of post that would be deleted. If don't found the is return message
     */
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable final int id) {
        postsService.deletePostById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/posts/undoUpdate")
    public ResponseEntity<HttpStatus> undoUpdatedPost() {
        postsService.undoUpdatedPostInActiveSession();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/posts/undoDeleted")
    public ResponseEntity<HttpStatus> undoDeletedPost() {
        postsService.undoDeletedPostInActiveSession();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/posts/undoAdd")
    public ResponseEntity<HttpStatus> undoAddedPost() {
        postsService.undoAddedPostInActiveSession();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
