package com.example.infrastructure.dbs.mongodb.repository;

import com.example.application.repository.PostMongoRepository;
import com.example.infrastructure.dbs.mongodb.entity.PostEntityMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PostDtoMongoRepository implements PostMongoRepository {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public PostDtoMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public PostEntityMongo getById(int idPostToReturn) {
        return mongoTemplate.findOne(getQueryToSearchById(idPostToReturn), PostEntityMongo.class);
    }

    @Override
    public void insert(PostEntityMongo newPostToInsert) {
        mongoTemplate.insert(newPostToInsert);
    }

    @Override
    public void deleteById(int idPostToDelete) {
        mongoTemplate.remove(getQueryToSearchById(idPostToDelete), PostEntityMongo.class);
    }

    @Override
    public void updateById(PostEntityMongo postUpdated) {
        mongoTemplate.save(postUpdated);
    }

    private Query getQueryToSearchById(int idPostToReturn){
        return Query.query(Criteria.where("_id").is(idPostToReturn));
    }
}
