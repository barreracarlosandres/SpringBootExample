package com.example.infrastructure.rest.controller;


import com.example.application.services.ServiceUndoPost;
import com.example.application.services.ServicePost;
import com.example.infrastructure.rest.dto.PostDto;
import com.example.infrastructure.rest.mapper.MapperPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {


    private final ServicePost servicePost;

    private final ServiceUndoPost serviceUndoPost;

    private final MapperPost mapperPost;

    /**
     * Dependency Injection
     * Nota: It´s not necessary use the @Autowared, I use just for explicit it
     */
    @Autowired
    public PostController(ServicePost servicePost, ServiceUndoPost serviceUndoPost,MapperPost mapperPost) {
        this.servicePost = servicePost;
        this.serviceUndoPost = serviceUndoPost;
        this.mapperPost = mapperPost;
    }

    /**
     * Show how to use RequestMapping for return all elements
     *
     * @return all Posts tha have the Array
     */
    @RequestMapping("/posts")
    public ResponseEntity<List<PostDto>> getPosts() {
        return new ResponseEntity<>(mapperPost.toDto(servicePost.getPosts()), HttpStatus.OK);
    }

    /**
     * Show how to use RequestMapping for return just an element
     *
     * @param id identifier of element in the Array
     * @return the Post Object that is equal to the id, if don´t find the id return message
     */
    @RequestMapping("/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable int id) {
        return new ResponseEntity<>(mapperPost.toDto(servicePost.getPostById(id)), HttpStatus.OK);
    }

    /**
     * Show how to update a data of Post, the input is a json that represent the Post Object
     *
     * @param postDto json that represent the Post Object tht would be added, then return a message.
     */
    @PostMapping("/posts")
    public ResponseEntity<HttpStatus> addPost(@RequestBody final PostDto postDto) {
        servicePost.addPost(mapperPost.toDomain(postDto));
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
        servicePost.updatePostById(mapperPost.toDomain(postDto), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Show how to delete a Post
     *
     * @param id identifier of post that would be deleted. If don't found the is return message
     */
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable final int id) {
        servicePost.deletePostById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/posts/undoUpdated")
    public ResponseEntity<HttpStatus> undoUpdatedPost() {
        serviceUndoPost.undoUpdatedPostInActiveSession();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/posts/undoDeleted")
    public ResponseEntity<HttpStatus> undoDeletedPost() {
        serviceUndoPost.undoDeletedPostInActiveSession();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/posts/undoAdded")
    public ResponseEntity<HttpStatus> undoAddedPost() {
        serviceUndoPost.undoAddedPostInActiveSession();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
