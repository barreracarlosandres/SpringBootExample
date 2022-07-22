package com.example.application.repository;

import com.example.infrastructure.dbs.mongodb.entity.MongoPostEntity;

public interface PostMongoRepository {

//    List<Post> getAllPosts();

    MongoPostEntity getPostMongoById(int idPostToFind);

    void addPostMongo(MongoPostEntity post);

    void deletePostMongoById(int idPostToDelete);

    void updatePostMongoById(MongoPostEntity postUpdated);



}
