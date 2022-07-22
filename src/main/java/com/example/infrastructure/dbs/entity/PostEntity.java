package com.example.infrastructure.dbs.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostEntity {
    private int idPost;
    private String title;
    private String body;
}
