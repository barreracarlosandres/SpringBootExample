package com.example.application.respository;

public interface PostUndo {
    void undoUpdatedPost();

    void undoDeletedPost();

    void undoAddedPost();
}
