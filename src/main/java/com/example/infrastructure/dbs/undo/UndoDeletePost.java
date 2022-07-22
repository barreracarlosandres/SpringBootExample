package com.example.infrastructure.dbs.undo;

import com.example.domain.Post;

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