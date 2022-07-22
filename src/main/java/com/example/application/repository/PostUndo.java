package com.example.application.repository;

public interface PostUndo {
    void undoUpdatedPost();

    void undoDeletedPost();

    void undoAddedPost();
}
