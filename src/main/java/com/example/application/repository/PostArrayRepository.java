package com.example.application.repository;


import com.example.domain.Post;

import java.util.List;

public interface PostArrayRepository {
    List<Post> getAllPosts();

    void addPost(Post newPostToAdd);

    Post getPostById(int idPostToFind);

    void deletePostById(int idPostToDelete);

    void updatePostById(Post postUpdated, int idPostToUpdate);

}
