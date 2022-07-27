package com.example.infrastructure.dbs.arraydb.repository;


import com.example.application.repository.PostArrayRepository;
import com.example.application.repository.PostUndo;
import com.example.common.domain.exceptions.RuntimeExceptionExistValue;
import com.example.common.infrastructure.exception.PostMessageExeptions;
import com.example.common.infrastructure.exception.RuntimeExceptionNullPost;
import com.example.domain.Post;
import com.example.infrastructure.dbs.arraydb.db.ArrayPosts;
import com.example.infrastructure.dbs.mapper.MapperDdo;
import com.example.infrastructure.dbs.undo.UndoDeletePost;
import com.example.infrastructure.dbs.undo.UndoInsertedPost;
import com.example.infrastructure.dbs.undo.UndoUpdatedPost;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PostArrayDboRepository implements PostArrayRepository, PostUndo {

    private final MapperDdo mapperDdo = new MapperDdo();

    private final ArrayPosts arrayPosts;

    @Autowired
    public PostArrayDboRepository(ArrayPosts arrayPosts) {
        this.arrayPosts = arrayPosts;
    }

    /**
     * Function to get all Posts
     *
     * @return List of Posts
     */
    @Override
    public List<Post> getAll() {
        return new ArrayList<>(mapperDdo.toDomain(arrayPosts.getAllPosts()));  // Do that to make inmutable the List
    }

    @Override
    public void insert(Post newPostToInsert) {

        int postId = this.getPositionInDBOfPost(newPostToInsert.getIdPost());

        if (postId != -1) {
            throw new RuntimeExceptionExistValue(PostMessageExeptions.YA_EXISTE_EL_POST);
        }
        arrayPosts.insert(mapperDdo.toDdo(newPostToInsert));
        UndoInsertedPost.addIdPostInserted(newPostToInsert.getIdPost());
    }

    @Override
    public Post getById(int idPostToFind) {
        int postId = getPositionInDBOfPost(idPostToFind);

        if (postId == -1) {
            throw new RuntimeExceptionNullPost(PostMessageExeptions.POST_NO_EXISTE);
        }
        return mapperDdo.toDomain(arrayPosts.get(postId));
    }

    @Override
    public void deleteById(int idPostToDelete) {

        int postId = getPositionInDBOfPost(idPostToDelete);
        if (postId == -1) {
            throw new RuntimeExceptionNullPost(PostMessageExeptions.POST_NO_EXISTE);
        } else {
            Post postDelete = mapperDdo.toDomain(arrayPosts.get(postId));
            UndoDeletePost.addBeforeDelete(postDelete);
            arrayPosts.remove(postId);
        }
    }

    @Override
    public void updateById(Post postUpdated, int idPostToUpdate) {
        int postId = getPositionInDBOfPost(idPostToUpdate);
        if (postId == -1) {
            throw new RuntimeExceptionNullPost(PostMessageExeptions.POST_NO_EXISTE);
        } else {
            Post postBeforeUpdate = mapperDdo.toDomain(arrayPosts.get(postId));
            UndoUpdatedPost.addPositionAndPostBeforeUpdate(postId, postBeforeUpdate);
            arrayPosts.update(postId, mapperDdo.toDdo(postUpdated));
        }
    }

    @Override
    public void undoLastInsert() {
        int idPostToDelete = UndoInsertedPost.getIdPostInserted();
        if (idPostToDelete != -1) {
            this.deleteById(UndoInsertedPost.getIdPostInserted());
        }
    }

    @Override
    public void undoLastDelete() {
        Post postDeleted = UndoDeletePost.getDeleted();
        if (postDeleted != null) {
            arrayPosts.insert(mapperDdo.toDdo(postDeleted));
        }
    }

    @Override
    public void undoLastUpdate() {
        Map<Integer, Post> data = UndoUpdatedPost.getPositionAndPostBeforeUpdated();
        Iterator<Integer> iterator = data.keySet().iterator();

        if (iterator.hasNext()) {
            Object key = iterator.next();
            arrayPosts.update(Integer.parseInt(key.toString()), mapperDdo.toDdo(data.get(key)));
        }
    }

    private int getPositionInDBOfPost(int idPost) {
        int finalPositionPostInArray = -1;

        finalPositionPostInArray = findPostInArray(idPost, finalPositionPostInArray);

        return finalPositionPostInArray;
    }

    private int findPostInArray(int idPost, int finalPositionPostInArray) {
        for (int positionArray = 0; positionArray < arrayPosts.size(); positionArray++) {
            if (arrayPosts.get(positionArray).getIdPost() == idPost) {
                finalPositionPostInArray = positionArray;
                break;
            }
        }
        return finalPositionPostInArray;
    }
}