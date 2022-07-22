package com.example.application.services;

import com.example.application.repository.PostUndo;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceUndoPost {
    private final PostUndo postUndo;

    @Autowired
    public ServiceUndoPost(PostUndo postUndo) {
        this.postUndo = postUndo;
    }

    public void undoUpdatedPostInActiveSession() {
        postUndo.undoUpdatedPost();
    }

    public void undoDeletedPostInActiveSession() {
        postUndo.undoDeletedPost();
    }

    public void undoAddedPostInActiveSession() {
        postUndo.undoAddedPost();
    }
}