package com.example.application.repository;

public interface PostUndo {
    void undoLastUpdate();

    void undoLastDelete();

    void undoLastInsert();
}
