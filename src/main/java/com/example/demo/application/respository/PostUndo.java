package com.example.demo.application.respository;

public interface PostUndo {
    void undoUpdatedPost();

    void undoDeletedPost();

    void undoAddedPost();
}
