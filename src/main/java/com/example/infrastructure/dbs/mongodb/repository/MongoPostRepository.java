package com.example.infrastructure.dbs.mongodb.repository;

import com.example.application.repository.PostMongoRepository;
import com.example.infrastructure.dbs.mongodb.entity.MongoPostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MongoPostRepository implements PostMongoRepository {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoPostRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public MongoPostEntity getPostMongoById(int idPostToFind) {
        return mongoTemplate.findOne(Query.query(Criteria.where("_id").is(idPostToFind)), MongoPostEntity.class);
    }

    @Override
    public void addPostMongo(MongoPostEntity post) {
        mongoTemplate.save(post);
    }

    @Override
    public void deletePostMongoById(int idPostToDelete) {
        MongoPostEntity mongoPostEntity = mongoTemplate.findOne(
                Query.query(Criteria.where("_id").is(idPostToDelete)), MongoPostEntity.class);
        mongoTemplate.remove(mongoPostEntity);
    }

    @Override
    public void updatePostMongoById(MongoPostEntity postUpdated) {
        mongoTemplate.save(postUpdated);
    }
}
