package com.example.infrastructure.rest.controller;


import com.example.application.services.ServicePost;
import com.example.application.services.ServiceUndoPost;
import com.example.infrastructure.dbs.mongodb.repository.PostDtoMongoRepository;
import com.example.infrastructure.rest.dto.PostDto;
import com.example.infrastructure.rest.mapper.MapperDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {


    private final ServicePost servicePost;
    private final ServiceUndoPost serviceUndoPost;
    private PostDtoMongoRepository postDtoMongoRepository;

    private final MapperDto mapperDto;

    /**
     * Dependency Injection
     * Nota: It´s not necessary use the @Autowared, I use just for explicit it
     */
    @Autowired
    public PostController(ServicePost servicePost, ServiceUndoPost serviceUndoPost, PostDtoMongoRepository postDtoMongoRepository, MapperDto mapperDto) {
        this.servicePost = servicePost;
        this.serviceUndoPost = serviceUndoPost;
        this.postDtoMongoRepository = postDtoMongoRepository;
        this.mapperDto = mapperDto;
    }

    /**
     * Show how to use RequestMapping for return all elements
     *
     * @return all Posts tha have the Array
     */
    @RequestMapping("/posts")
    public ResponseEntity<List<PostDto>> getPosts() {
        return new ResponseEntity<>(mapperDto.toDto(servicePost.get()), HttpStatus.OK);
    }

    /**
     * Show how to use RequestMapping for return just an element
     *
     * @param id identifier of element in the Array
     * @return the Post Object that is equal to the id, if don´t find the id return message
     */
    @RequestMapping("/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable int id) {
        return new ResponseEntity<>(mapperDto.toDto(servicePost.getById(id)), HttpStatus.OK);
    }

    @RequestMapping("/posts/mongo/{id}")
    public ResponseEntity<PostDto> getPostMongoById(@PathVariable int id) {
        return new ResponseEntity<>(mapperDto.toDto(postDtoMongoRepository.getById(id)), HttpStatus.OK);
    }

    /**
     * Show how to update a data of Post, the input is a json that represent the Post Object
     *
     * @param postDto json that represent the Post Object tht would be added, then return a message.
     */
    @PostMapping("/posts")
    public ResponseEntity<HttpStatus> addPost(@RequestBody final PostDto postDto) {
        servicePost.insert(mapperDto.toDomain(postDto));
        postDtoMongoRepository.insert(mapperDto.toMongo(postDto));
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
        servicePost.updateById(mapperDto.toDomain(postDto), id);
        postDtoMongoRepository.updateById(mapperDto.toMongo(postDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Show how to delete a Post
     *
     * @param id identifier of post that would be deleted. If don't found the is return message
     */
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable final int id) {
        servicePost.deleteById(id);
//        FIXME no pasa los test al eliminar de BD Mong DB, arreglar
//        postDtoMongoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/posts/undoUpdated")
    public ResponseEntity<HttpStatus> undoUpdatedPost() {
        serviceUndoPost.undoUpdatedInActiveSession();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/posts/undoDeleted")
    public ResponseEntity<HttpStatus> undoDeletedPost() {
        serviceUndoPost.undoDeletedInActiveSession();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/posts/undoAdded")
    public ResponseEntity<HttpStatus> undoAddedPost() {
        serviceUndoPost.undoAddedInActiveSession();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
