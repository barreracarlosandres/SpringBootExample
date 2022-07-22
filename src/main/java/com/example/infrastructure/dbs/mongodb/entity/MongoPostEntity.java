package com.example.infrastructure.dbs.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "posts")
@AllArgsConstructor
@Getter
@Setter
public class MongoPostEntity {

    @Id
    private int id_post;

    private String title;
    private String body;

}

