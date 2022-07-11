package com.example.demo.infrastructure.db.respository;

import com.example.demo.domain.Post;

import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

public class LastTransactionPost {

    private static int idPostAdded = -1;
    private static Post postDeleted;
    private static Post postBeforeUpdateTitle;

    private static Integer positionPostInArray = -1;
    private static Map<Integer, Post> updatedPost = new HashMap<>();

    public static void addIdPostAdded(int idPostAdded){
        LastTransactionPost.idPostAdded = idPostAdded;
    }

    public static int getIdPostAdded(){
        return LastTransactionPost.idPostAdded;
    }

    public static void addLastPostDeleted(Post postDeleted) {
        LastTransactionPost.postDeleted = postDeleted;
    }

    public static Post getPostDeleted() {
        return LastTransactionPost.postDeleted;
    }

    public static void addPostUpdatedTitle( Integer positionPostInArray, Post postBeforeUpdateTitle) {
        LastTransactionPost.updatedPost.put(positionPostInArray, postBeforeUpdateTitle);
//        LastTransactionPost.positionPostInArray = positionPostInArray;
//        LastTransactionPost.postBeforeUpdateTitle = postBeforeUpdateTitle;
    }

    public static Map<Integer, Post> getPostBeforeUpdatedTitle() {
        return LastTransactionPost.updatedPost;
    }
}
