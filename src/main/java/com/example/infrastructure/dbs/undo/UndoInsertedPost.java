package com.example.infrastructure.dbs.undo;

public class UndoInsertedPost {

    private static int idPostInserted = -1;

    private UndoInsertedPost() {
    }

    public static void addIdPostInserted(int idPostInserted){
        UndoInsertedPost.idPostInserted = idPostInserted;
    }

    public static int getIdPostInserted(){
        return UndoInsertedPost.idPostInserted;
    }

}
