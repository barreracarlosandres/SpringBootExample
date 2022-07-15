package com.example.demo.application.services;

import com.example.demo.application.respository.PostRepository;
import com.example.demo.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServicePost {

    private final PostRepository postRepository;

    /**
     * Dependency Injection
     * //* @param initialData initial dato of Posts
     */
    @Autowired
    public ServicePost(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * Function to get all Posts
     *
     * @return List of Posts
     */
    public List<Post> getPosts() {
        return postRepository.getAllPosts();
    }

    /**
     * Function to get the post by id
     *
     * @param idPostToFind the id, unique, that represent the Post
     * @return Post found in the array
     */
    public Post getPostById(int idPostToFind) {
        return postRepository.getPostById(idPostToFind);
    }

    /**
     * Funtion to add Post to the Array
     *
     * @param postToAdd object that represent the Post Object to be added
     */
    public void addPost(Post postToAdd) {
        postRepository.addPost(postToAdd);
    }

    /**
     * Function to update Post
     *
     * @param postUpdated   Object that correspond to the new data of Post to be updated
     * @param idPostToUpdate id that represent Post to be updated
     */
    public void updatePostById(Post postUpdated, int idPostToUpdate) {
        postRepository.updatePostById(postUpdated, idPostToUpdate);
    }

    /**
     * Function for delete the Post
     *
     * @param idPostToDelete id that represent the Post to be deleted
     */
    public void deletePostById(int idPostToDelete) {
        postRepository.deletePostById(idPostToDelete);
    }
}
