package com.example.application.fabric;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommandPost {
    private int idPost;
    private String title;
    private String body;
}
