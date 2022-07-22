package com.example.infrastructure.dbs.undo;

import com.example.domain.Post;

import java.util.HashMap;
import java.util.Map;

public class UndoUpdatePost {
    private static final Map<Integer, Post> updatedPost = new HashMap<>();

    private UndoUpdatePost() {
    }

    public static void addPositionAndPostBeforeUpdate(Integer positionPostInArray, Post postBeforeUpdateTitle) {
        UndoUpdatePost.updatedPost.put(positionPostInArray, postBeforeUpdateTitle);
    }

    public static Map<Integer, Post> getPositionAndPostBeforeUpdated() {
        return UndoUpdatePost.updatedPost;
    }
}