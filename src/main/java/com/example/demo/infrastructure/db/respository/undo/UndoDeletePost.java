package com.example.demo.infrastructure.db.respository.undo;

import com.example.demo.domain.Post;

public class UndoDeletePost {
    private static Post postDeleted;

    private UndoDeletePost() {
    }

    public static void addBeforeDelete(Post postDeleted) {
        UndoDeletePost.postDeleted = postDeleted;
    }

    public static Post getDeleted() {
        return UndoDeletePost.postDeleted;
    }
}