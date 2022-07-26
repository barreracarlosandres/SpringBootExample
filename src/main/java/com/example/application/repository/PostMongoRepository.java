package com.example.application.repository;

import com.example.infrastructure.dbs.mongodb.entity.PostEntityMongo;

public interface PostMongoRepository {

//    List<Post> getAllPosts();
//    TODO pendiente por implementar este

    PostEntityMongo getById(int idPostToReturn);

    void insert(PostEntityMongo newPostToInsert);

    void deleteById(int idPostToDelete);

    void updateById(PostEntityMongo postUpdated);

}
