package com.example.application.services;

import com.example.application.repository.MongoServicePost;
import com.example.domain.Post;
import com.example.infrastructure.dbs.mongodb.repository.MongoPostRepository;
import com.example.infrastructure.rest.mapper.MapperPost;
import org.springframework.beans.factory.annotation.Autowired;

//@Service
public class MongoPostServiceImpl implements MongoServicePost {

    private MongoPostRepository mongoPostRepository;

    //@Autowired
    public MongoPostServiceImpl(MongoPostRepository mongoPostRepository) {
        this.mongoPostRepository = mongoPostRepository;
    }

    private MapperPost mapperPost = new MapperPost();

    public void addPostMongo(Post post) {
        mongoPostRepository.addPostMongo(mapperPost.toMongo(post));
    }
}
