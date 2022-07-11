package com.example.demo.infrastructure.db.respository;


import com.example.demo.application.respository.PostRepository;
import com.example.demo.common.domain.exceptions.RuntimeExceptionExistValue;
import com.example.demo.common.infrastructure.exception.RuntimeExceptionNullPost;
import com.example.demo.domain.Post;
import com.example.demo.infrastructure.db.dbo.PostEntity;
import com.example.demo.infrastructure.db.mapper.MapperPostEntity;

import java.util.ArrayList;
import java.util.List;

public class PostDboRepository implements PostRepository {

    private final MapperPostEntity mapperPostEntity = new MapperPostEntity();

    DBArrayPosts dbArrayPosts = DBArrayPosts.getInstance();


    /**
     * Function to get all Posts
     *
     * @return List of Posts
     */
    @Override
    public List<Post> getAllPosts() {
        return new ArrayList<>(mapperPostEntity.toDomain(dbArrayPosts.getAllPosts()));  // Do that to make inmutable the List
//        TODO mejorar este método
    }

    @Override
    public boolean addPost(Post newPostToAdd) {

        int postId = this.getPositionInDBOfPost(newPostToAdd.getIdPost());

        if (postId != -1) {
            throw new RuntimeExceptionExistValue("Ya existe el Post");
        }
        return dbArrayPosts.add(mapperPostEntity.toDdo(newPostToAdd));
    }

    @Override
    public Post getPostById(int idPostToFind) {
        int postId = getPositionInDBOfPost(idPostToFind);

        if (postId == -1) {
            throw new RuntimeExceptionNullPost("Post no existe");
        }
        return mapperPostEntity.toDomain(dbArrayPosts.get(postId));
    }

    @Override
    public boolean deletePostById(int idPostToDelete) {

        int postId = getPositionInDBOfPost(idPostToDelete);
        if(idPostToDelete == -1){
            throw new RuntimeExceptionNullPost("Post no existe");
        } else {
            dbArrayPosts.remove(postId);
        }
        return true;

//        boolean isDeleted = false;
//        for (int i = 0; i < dbArrayPosts.size(); i++) {
//            PostEntity tempPostEntity = dbArrayPosts.get(i);
//            if (tempPostEntity.getIdPost() == idPostToDelete) {
//                dbArrayPosts.remove(i);
//                isDeleted = true;
//                break;
//            }
//        }
//        return isDeleted;
//         TODO pendiente por ajustar este método para usar el de buscar
//          - se debería enviar excepción
    }

    @Override
    public boolean updatePostById(Post postUpdated, int idPostToUpdate) {
        int postId = getPositionInDBOfPost(idPostToUpdate);
        if (postId == -1) {
            throw new RuntimeExceptionNullPost("Post no existe");
        } else {
            dbArrayPosts.set(postId, mapperPostEntity.toDdo(postUpdated));
        }
        return true;
    }

    private int getPositionInDBOfPost(int idPost) {
        int positionPost = -1;
        for (int i = 0; i < dbArrayPosts.size(); i++) {
            if (dbArrayPosts.get(i).getIdPost() == idPost) {
                positionPost = i;
                break;
            }
        }
        return positionPost;
    }
}