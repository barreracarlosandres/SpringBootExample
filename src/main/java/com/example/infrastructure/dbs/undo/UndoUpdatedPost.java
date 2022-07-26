package com.example.infrastructure.dbs.undo;

import com.example.domain.Post;

import java.util.HashMap;
import java.util.Map;

public class UndoUpdatedPost {
    private static final Map<Integer, Post> updatedPost = new HashMap<>();

    private UndoUpdatedPost() {
    }

    public static void addPositionAndPostBeforeUpdate(Integer positionPostInArray, Post postBeforeUpdateTitle) {
        UndoUpdatedPost.updatedPost.put(positionPostInArray, postBeforeUpdateTitle);
    }

    public static Map<Integer, Post> getPositionAndPostBeforeUpdated() {
        return UndoUpdatedPost.updatedPost;
    }
}