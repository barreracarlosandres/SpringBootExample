package com.example.demo.application.respository;


import com.example.demo.domain.Post;

import java.util.List;

public interface PostRepository {
    List<Post> getAllPosts();

    void addPost(Post newPostToAdd);

    Post getPostById(int idPostToFind);

    void deletePostById(int idPostToDelete);

    void updatePostById(Post postUpdated, int idPostToUpdate);

}
