package com.example.infrastructure.dbs.mongodb.repository;

import com.example.application.repository.PostMongoRepository;
import com.example.infrastructure.dbs.mongodb.entity.MongoPostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MongoPostRepository implements PostMongoRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoPostRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void addPostMongo(MongoPostEntity post) {
        mongoTemplate.save(post);
    }
}
