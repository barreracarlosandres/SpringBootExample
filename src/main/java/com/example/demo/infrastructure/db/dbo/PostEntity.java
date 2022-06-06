package com.example.demo.infrastructure.db.dbo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostEntity {
    private int idPost;
    private String title;
    private String body;
}
