package com.example.application.repository;


import com.example.domain.Post;

import java.util.List;

public interface PostArrayRepository {
    List<Post> getAll();

    void insert(Post newPostToInsert);

    Post getById(int idPostToFind);

    void deleteById(int idPostToDelete);

    void updateById(Post postUpdated, int idPostToUpdate);
}
