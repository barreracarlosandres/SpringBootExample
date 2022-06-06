package com.example.demo.infrastructure.db.respository;


import com.example.demo.application.respository.PostRepository;
import com.example.demo.common.domain.exceptions.RuntimeExceptionExistValue;
import com.example.demo.common.infrastructure.exception.RuntimeExceptionNullPost;
import com.example.demo.domain.Post;
import com.example.demo.infrastructure.db.dbo.PostEntity;
import com.example.demo.infrastructure.db.mapper.MapperPostEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostDboRepository implements PostRepository {

    private final MapperPostEntity mapperPostEntity = new MapperPostEntity();

    /**
     * Method use for initializer the Posts
     */
    private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    private List<PostEntity> postsList = new ArrayList<>(Arrays.asList(new PostEntity(1, "accounts", getDemoText()), new PostEntity(2, "chevy", getDemoText()), new PostEntity(3, "cooking", getDemoText()), new PostEntity(4, "not", getDemoText()), new PostEntity(5, "know", getDemoText())));
//    FIXME sacar esta lista de objetos en una clase


    /**
     * Function to get all Posts
     *
     * @return List of Posts
     */
    @Override
    public List<Post> getPosts() {
        return new ArrayList<>(mapperPostEntity.toDomain(this.postsList));  // Do that to make inmutable the List
//        TODO mejorar este método
    }

    @Override
    public boolean addPost(Post post) {

        int postId = getPositionPost(post.getIdPost());

        if (postId != -1) {
            throw new RuntimeExceptionExistValue("Ya existe el Post");
        }
        return this.postsList.add(mapperPostEntity.toDdo(post));
    }

    @Override
    public Post getPostById(int idPost) {
        int postId = getPositionPost(idPost);

        if (postId == -1) {
            throw new RuntimeExceptionNullPost("Post no existe");
        }
        return mapperPostEntity.toDomain(postsList.get(postId));
    }

    @Override
    public boolean deletePostById(int idPost) {
        boolean isDeleted = false;
        for (int i = 0; i < postsList.size(); i++) {
            PostEntity tempPostEntity = postsList.get(i);
            if (tempPostEntity.getIdPost() == idPost) {
                postsList.remove(i);
                isDeleted = true;
                break;
            }
        }
        return isDeleted;
//         TODO pendiente por ajustar este método para usar el de buscar
//          - se debería enviar excepción
    }

    @Override
    public boolean updatePostById(Post post, int idPost) {
        int postId = getPositionPost(idPost);
        if (postId == -1) {
            throw new RuntimeExceptionNullPost("Post no existe");
        } else {
            postsList.set(postId, mapperPostEntity.toDdo(post));
        }
        return true;
    }

    /**
     * @return The Lorem Ipsum text
     */
    private static String getDemoText() {
        return PostDboRepository.LOREM_IPSUM;
    }

    private int getPositionPost(int idPost) {
        int positionPost = -1;
        for (int i = 0; i < postsList.size(); i++) {
            if (postsList.get(i).getIdPost() == idPost) {
                positionPost = i;
                break;
            }
        }
        return positionPost;
    }
}