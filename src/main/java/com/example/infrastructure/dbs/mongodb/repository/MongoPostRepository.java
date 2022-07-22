package com.example.infrastructure.dbs.mongodb.repository;

import com.example.infrastructure.dbs.mongodb.entity.MongoPostEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoPostRepository extends MongoRepository<MongoPostEntity, Integer> {

    void addPostMongo(MongoPostEntity mongoPostEntity);

}
