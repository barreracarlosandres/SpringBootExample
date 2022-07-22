package com.example.infrastructure.dbs.arraydb.repository;


import com.example.application.repository.PostUndo;
import com.example.common.infrastructure.exception.PostMessageExeptions;
import com.example.application.repository.PostArrayRepository;
import com.example.common.domain.exceptions.RuntimeExceptionExistValue;
import com.example.common.infrastructure.exception.RuntimeExceptionNullPost;
import com.example.domain.Post;
import com.example.infrastructure.dbs.mapper.MapperPostEntity;
import com.example.infrastructure.dbs.arraydb.db.ArrayPosts;
import com.example.infrastructure.dbs.undo.UndoAddedPost;
import com.example.infrastructure.dbs.undo.UndoDeletePost;
import com.example.infrastructure.dbs.undo.UndoUpdatePost;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PostArrayDboRepository implements PostArrayRepository, PostUndo {

    private final MapperPostEntity mapperPostEntity = new MapperPostEntity();

    ArrayPosts arrayPosts = ArrayPosts.getInstance();

    /**
     * Function to get all Posts
     *
     * @return List of Posts
     */
    @Override
    public List<Post> getAllPosts() {
        return new ArrayList<>(mapperPostEntity.toDomain(arrayPosts.getAllPosts()));  // Do that to make inmutable the List
    }

    @Override
    public void addPost(Post newPostToAdd) {

        int postId = this.getPositionInDBOfPost(newPostToAdd.getIdPost());

        if (postId != -1) {
            throw new RuntimeExceptionExistValue(PostMessageExeptions.YA_EXISTE_EL_POST);
        }
        arrayPosts.add(mapperPostEntity.toDdo(newPostToAdd));
        UndoAddedPost.addIdPostAdded(newPostToAdd.getIdPost());
    }

    @Override
    public Post getPostById(int idPostToFind) {
        int postId = getPositionInDBOfPost(idPostToFind);

        if (postId == -1) {
            throw new RuntimeExceptionNullPost(PostMessageExeptions.POST_NO_EXISTE);
        }
        return mapperPostEntity.toDomain(arrayPosts.get(postId));
    }

    @Override
    public void deletePostById(int idPostToDelete) {

        int postId = getPositionInDBOfPost(idPostToDelete);
        if (postId == -1) {
            throw new RuntimeExceptionNullPost(PostMessageExeptions.POST_NO_EXISTE);
        } else {
            Post postDelete = mapperPostEntity.toDomain(arrayPosts.get(postId));
            UndoDeletePost.addBeforeDelete(postDelete);
            arrayPosts.remove(postId);
        }
    }

    @Override
    public void updatePostById(Post postUpdated, int idPostToUpdate) {
        int postId = getPositionInDBOfPost(idPostToUpdate);
        if (postId == -1) {
            throw new RuntimeExceptionNullPost(PostMessageExeptions.POST_NO_EXISTE);
        } else {
            Post postBeforeUpdate = mapperPostEntity.toDomain(arrayPosts.get(postId));
            UndoUpdatePost.addPositionAndPostBeforeUpdate(postId, postBeforeUpdate);
            arrayPosts.set(postId, mapperPostEntity.toDdo(postUpdated));
        }
    }

    @Override
    public void undoAddedPost() {
        int idPostToDelete = UndoAddedPost.getIdPostAdded();
        if (idPostToDelete != -1) {
            this.deletePostById(UndoAddedPost.getIdPostAdded());
        }
    }

    @Override
    public void undoDeletedPost() {
        Post postDeleted = UndoDeletePost.getDeleted();
        if (postDeleted != null) {
            arrayPosts.add(mapperPostEntity.toDdo(postDeleted));
        }
    }

    @Override
    public void undoUpdatedPost() {
        Map<Integer, Post> data = UndoUpdatePost.getPositionAndPostBeforeUpdated();
        Iterator<Integer> iterator = data.keySet().iterator();

        if (iterator.hasNext()) {
            Object key = iterator.next();
            arrayPosts.set(Integer.parseInt(key.toString()), mapperPostEntity.toDdo(data.get(key)));
        }
    }

    private int getPositionInDBOfPost(int idPost) {
        int positionPost = -1;
        for (int i = 0; i < arrayPosts.size(); i++) {
            if (arrayPosts.get(i).getIdPost() == idPost) {
                positionPost = i;
                break;
            }
        }
        return positionPost;
    }
}