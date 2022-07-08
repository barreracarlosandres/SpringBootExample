package com.example.demo.application.respository;


import com.example.demo.domain.Post;

import java.util.List;

public interface PostRepository {
    List<Post> getPosts();

    boolean addPost(Post newPostToAdd);

    Post getPostById(int idPostToFind);

    boolean deletePostById(int idPostToDelete);

    boolean updatePostById(Post postUpdated, int idPostToUpdate);
}
