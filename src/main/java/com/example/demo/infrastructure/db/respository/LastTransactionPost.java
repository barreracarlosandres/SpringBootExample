package com.example.demo.infrastructure.db.respository;

import com.example.demo.domain.Post;

public class LastTransactionPost {

    private static int idPostAdded = -1;
    private static Post postDeleted;

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
}
