package com.example.demo.application.respository;


import com.example.demo.domain.Post;

import java.util.List;

public interface PostRepository {
    List<Post> getPosts();

    boolean addPost(Post post);

    Post getPostById(int idPost);

    boolean deletePostById(int idPost);

    boolean updatePostById(Post post, int idPost);
}
