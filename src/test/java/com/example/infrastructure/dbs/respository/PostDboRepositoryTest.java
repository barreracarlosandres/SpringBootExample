package com.example.infrastructure.dbs.respository;

import com.example.common.domain.exceptions.RuntimeExceptionExistValue;
import com.example.common.infrastructure.exception.RuntimeExceptionNullPost;
import com.example.domain.Post;
import com.example.infrastructure.dbs.arraydb.repository.PostArrayDboRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class PostDboRepositoryTest {

    private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";


    @Test
    void addPostAndThenUndo() {
        // arrange
        PostArrayDboRepository postDboRepository = new PostArrayDboRepository();
        Post post = new Post(100, "tittle", "body");
        postDboRepository.addPost(post);
        postDboRepository.undoAddedPost();
        // act - assert
        Exception exception = Assertions.assertThrows(RuntimeExceptionNullPost.class, () -> postDboRepository.getPostById(100));
        Assertions.assertEquals("Post no existe", exception.getMessage());
    }

    @Test
    void tryToaAdPostThatExits() {
        // arrange
        PostArrayDboRepository postDboRepository = new PostArrayDboRepository();
        Post post = new Post(1, "tittle", "body");

        // act - assert
        Exception exception = Assertions.assertThrows(RuntimeExceptionExistValue.class, () ->
                postDboRepository.addPost(post));
        Assertions.assertEquals("Ya existe el Post", exception.getMessage());
    }

    @Test
    void deletePostByIdAndThenUndo() {
        // arrange
        PostArrayDboRepository postDboRepository = new PostArrayDboRepository();
        int idPostToDelete = 5;
        // act
        postDboRepository.deletePostById(idPostToDelete);
        postDboRepository.undoDeletedPost();
        // assert
        Assertions.assertEquals(postDboRepository.getPostById(idPostToDelete).getIdPost(), idPostToDelete);
    }

    @Test
    void updatePostAndThenUndo() {
        // arrange
        PostArrayDboRepository postDboRepository = new PostArrayDboRepository();
        Post postUpdated = new Post(5, "titulo original", "body original");
        int idPostBeforeUpdated = postDboRepository.getPostById(postUpdated.getIdPost()).getIdPost();
        String titleBeforeUpdated = postDboRepository.getPostById(postUpdated.getIdPost()).getTitle();
        String bodyBeforeUpdated = postDboRepository.getPostById(postUpdated.getIdPost()).getBody();
        // act
        postDboRepository.updatePostById(postUpdated, postUpdated.getIdPost());
        postDboRepository.undoUpdatedPost();
        // assert
        Assertions.assertAll("post",
                () -> Assertions.assertEquals(5, idPostBeforeUpdated),
                () -> Assertions.assertEquals("know", titleBeforeUpdated),
                () -> Assertions.assertEquals(PostDboRepositoryTest.LOREM_IPSUM, bodyBeforeUpdated));
    }

    @Test
    void tryToUpdatePostThatNOExist() {
        // arrange
        PostArrayDboRepository postDboRepository = new PostArrayDboRepository();
        int idPostToDelete = 100;
        Post postUpdated = new Post(idPostToDelete, "titulo original", "body original");
        // act - assert
        Exception exception = Assertions.assertThrows(RuntimeExceptionNullPost.class, () ->
                postDboRepository.updatePostById(postUpdated, idPostToDelete));
        Assertions.assertEquals("Post no existe", exception.getMessage());
    }

    @Test
    void tryDeletePostThatNotExist() {
        // arrange
        int idPostToDelete = 100;
        PostArrayDboRepository postDboRepository = new PostArrayDboRepository();
        // act - assert
        Exception exception = Assertions.assertThrows(RuntimeExceptionNullPost.class, () ->
                postDboRepository.deletePostById(idPostToDelete));
        Assertions.assertEquals("Post no existe", exception.getMessage());
    }

    @Test
    void getAllPosts() {

        // arrange
        PostArrayDboRepository postDboRepository = new PostArrayDboRepository();
        // act
        List<Post> posts = postDboRepository.getAllPosts();
        // assert
        Assertions.assertTrue(posts.size() > 3);
    }
}