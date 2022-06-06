package com.example.demo.services;

import com.example.demo.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostsService{

    PostRespository postRespository;

    /**
     * Dependency Injection
     //* @param initialData initial dato of Posts
     */
    @Autowired
    public PostsService(PostRespository initialData) {
        this.postRespository = initialData;
    }

    /**
     * Function to get all Posts
     *
     * @return List of Posts
     */
    public List<Post> getPosts(){
        return postRespository.getPosts();
    }

    /**
     * Function to get the post by id
     *
     * @param idPost the id, unique, that represent the Post
     * @return Post found in the array
     */
    public Post getPostById(int idPost){
        return postRespository.getPostById( idPost );
    }

    /**
     * Funtion to add Post to the Array
     * @param listElement object that represent the Post Object to be added
     */
    public boolean addPost(Post listElement) {
        return postRespository.addPost(listElement);
    }

    /**
     * Function to update Post
     * @param post Object that correspond to the new data of Post to be updated
     * @param idPost id that represent Post to be updated
     */
    public boolean updatePostById(Post post, int idPost) {
        return postRespository.updatePostById(post, idPost);
    }

    /**
     * Function for delete the Post
     *
     * @param idPost id that represent the Post to be deleted
     */
    public boolean deletePostById(int idPost) {
        return postRespository.deletePostById(idPost);
    }




}
