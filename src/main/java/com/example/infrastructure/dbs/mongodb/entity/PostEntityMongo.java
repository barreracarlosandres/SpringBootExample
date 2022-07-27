package com.example.infrastructure.dbs.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "posts")
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostEntityMongo {

    @Id
    private int id_post;

    private String title;
    private String body;

}

