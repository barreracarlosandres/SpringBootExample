package com.example.demo.infrastructure.db.respository;


import com.example.demo.application.respository.PostRepository;
import com.example.demo.common.domain.exceptions.RuntimeExceptionExistValue;
import com.example.demo.common.infrastructure.exception.PostMessageExeptions;
import com.example.demo.common.infrastructure.exception.RuntimeExceptionNullPost;
import com.example.demo.domain.Post;
import com.example.demo.infrastructure.db.mapper.MapperPostEntity;
import com.example.demo.infrastructure.db.respository.db.ArrayPosts;
import com.example.demo.infrastructure.db.respository.undo.UndoAddedPost;
import com.example.demo.infrastructure.db.respository.undo.UndoDeletePost;
import com.example.demo.infrastructure.db.respository.undo.UndoUpdatePost;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PostDboRepository implements PostRepository {


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
            Post postDelete = this.getPostById(idPostToDelete);
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
            Post postBeforeUpdate = getPostById(idPostToUpdate);
            UndoUpdatePost.addPositionAndPostBeforeUpdate(Integer.valueOf(postId), postBeforeUpdate);
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