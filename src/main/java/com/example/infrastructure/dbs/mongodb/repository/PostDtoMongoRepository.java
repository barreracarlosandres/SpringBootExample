package com.example.infrastructure.dbs.mongodb.repository;

import com.example.application.repository.PostMongoRepository;
import com.example.common.domain.exceptions.RuntimeExceptionExistValue;
import com.example.common.domain.validations.Validations;
import com.example.common.infrastructure.ExceptionResponse;
import com.example.common.infrastructure.exception.PostMessageExeptions;
import com.example.common.infrastructure.exception.RuntimeExceptionNullPost;
import com.example.infrastructure.dbs.mongodb.entity.PostEntityMongo;
import com.mongodb.client.result.DeleteResult;
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
        PostEntityMongo postToReturn = mongoTemplate.findOne(getQueryToSearchById(idPostToReturn), PostEntityMongo.class);
        if(postToReturn == null){
            throw new RuntimeExceptionNullPost(PostMessageExeptions.POST_NO_EXISTE);
        }
        return  postToReturn;
    }

    @Override
    public void insert(PostEntityMongo newPostToInsert) {
        // TODO valida porqué no pasan los test cuando se cambia save por insert
        PostEntityMongo savedPost = mongoTemplate.save(newPostToInsert);
        if(savedPost == null){
            throw new RuntimeExceptionNullPost(PostMessageExeptions.POST_NO_EXISTE);
        }
    }

    @Override
    public void deleteById(int idPostToDelete) {
        DeleteResult deleteResult = mongoTemplate.remove(getQueryToSearchById(idPostToDelete), PostEntityMongo.class);
        if (deleteResult.getDeletedCount() == 0) {
            throw new RuntimeExceptionExistValue(PostMessageExeptions.POST_NO_EXISTE);
        }
    }

    @Override
    public void updateById(PostEntityMongo postUpdated) {
        mongoTemplate.save(postUpdated);
    }

    private Query getQueryToSearchById(int idPostToReturn) {
        return Query.query(Criteria.where("_id").is(idPostToReturn));
    }
}
