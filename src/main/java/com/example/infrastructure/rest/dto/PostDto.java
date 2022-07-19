package com.example.infrastructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostDto {
    private int idPost;
    private String title;
    private String body;
}
