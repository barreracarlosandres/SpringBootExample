package com.example.application.repository;

import com.example.domain.Post;
import com.example.infrastructure.dbs.mongodb.entity.MongoPostEntity;

public interface PostMongoRepository {

    void addPostMongo(MongoPostEntity post);

}
