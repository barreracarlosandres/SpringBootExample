package com.example.infrastructure.dbs.undo;

public class UndoAddedPost {

    private static int idPostAdded = -1;

    private UndoAddedPost() {
    }

    public static void addIdPostAdded(int idPostAdded){
        UndoAddedPost.idPostAdded = idPostAdded;
    }

    public static int getIdPostAdded(){
        return UndoAddedPost.idPostAdded;
    }

}