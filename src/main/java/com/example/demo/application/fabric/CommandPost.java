package com.example.demo.application.fabric;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
public class CommandPost {
    private int idPost;
    private String title;
    private String body;
}
