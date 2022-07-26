package com.example.application.services;

import com.example.application.repository.PostArrayRepository;
import com.example.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServicePost {

    private final PostArrayRepository postRepository;

    /**
     * Dependency Injection
     * //* @param initialData initial dato of Posts
     */
    @Autowired
    public ServicePost(PostArrayRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * Function to get all Posts
     *
     * @return List of Posts
     */
    public List<Post> get() {
        return postRepository.getAll();
    }

    /**
     * Function to get the post by id
     *
     * @param idPostToReturn the id, unique, that represent the Post
     * @return Post found in the array
     */
    public Post getById(int idPostToReturn) {
        return postRepository.getById(idPostToReturn);
    }

    /**
     * Funtion to add Post to the Array
     *
     * @param postToInsert object that represent the Post Object to be added
     */
    public void insert(Post postToInsert) {
        postRepository.insert(postToInsert);
    }

    /**
     * Function to update Post
     *
     * @param postUpdated   Object that correspond to the new data of Post to be updated
     * @param idPostToUpdate id that represent Post to be updated
     */
    public void updateById(Post postUpdated, int idPostToUpdate) {
        postRepository.updateById(postUpdated, idPostToUpdate);
    }

    /**
     * Function for delete the Post
     *
     * @param idPostToDelete id that represent the Post to be deleted
     */
    public void deleteById(int idPostToDelete) {
        postRepository.deleteById(idPostToDelete);
    }
}
