package com.example.demo.services;

import com.example.demo.entity.Post;

import java.util.List;

public interface DaoPost {
    List<Post> getPosts();

    boolean addPost(Post post);

    Post getPostById(int idPost);

    boolean deletePostById(int idPost);

    boolean updatePostById(Post post, int idPost);
}
