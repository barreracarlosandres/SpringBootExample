package com.example.application.services;

import com.example.application.repository.PostUndo;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceUndoPost {
    private final PostUndo postUndo;

    @Autowired
    public ServiceUndoPost(PostUndo postUndo) {
        this.postUndo = postUndo;
    }

    public void undoUpdatedInActiveSession() {
        postUndo.undoLastUpdate();
    }

    public void undoDeletedInActiveSession() {
        postUndo.undoLastDelete();
    }

    public void undoAddedInActiveSession() {
        postUndo.undoLastInsert();
    }
}