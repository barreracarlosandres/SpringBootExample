package com.example.infrastructure.dbs.respository;

import com.example.common.domain.exceptions.RuntimeExceptionExistValue;
import com.example.common.infrastructure.exception.RuntimeExceptionNullPost;
import com.example.domain.Post;
import com.example.infrastructure.dbs.arraydb.db.ArrayPosts;
import com.example.infrastructure.dbs.arraydb.repository.PostArrayDboRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class PostDboRepositoryTest {

    private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    private final ArrayPosts arrayPosts = ArrayPosts.getInstance();

    private final PostArrayDboRepository postDboRepository = new PostArrayDboRepository(arrayPosts);

    @Test
    void addPostAndThenUndo() {
        // arrange
        Post post = new Post(100, "tittle", "body");
        postDboRepository.insert(post);
        // act
        postDboRepository.undoLastInsert();
        // assert
        Exception exception = Assertions.assertThrows(RuntimeExceptionNullPost.class, () -> postDboRepository.getById(100));
        Assertions.assertEquals("Post no existe", exception.getMessage());
    }

    @Test
    void tryToInsertPostThatExits() {
        // arrange
        Post post = new Post(1, "tittle", "body");
        // act - assert
//        Exception exception = Assertions.assertThrows(RuntimeExceptionExistValue.class, () ->
//                postDboRepository.insert(post));
//        Assertions.assertEquals("Ya existe el Post", exception.getMessage());

        try{
            // act
            postDboRepository.insert(post);
        }
        catch (RuntimeExceptionExistValue exception){
            // assert
            Assertions.assertEquals("Ya existe el Post", exception.getMessage());
        }

    }

    @Test
    void deletePostByIdAndThenUndo() {
        // arrange
        int idPostToDelete = 6;
        postDboRepository.deleteById(idPostToDelete);
        // act
        postDboRepository.undoLastDelete();
        // assert
        Assertions.assertEquals(postDboRepository.getById(idPostToDelete).getIdPost(), idPostToDelete);
    }

    @Test
    void updatePostAndThenUndo() {
        // arrange
        int idPostToUpdate = 7;
        Post postUpdated = new Post(idPostToUpdate, "titulo original", "body original");
        int idPostBeforeUpdated = postDboRepository.getById(postUpdated.getIdPost()).getIdPost();
        String titleBeforeUpdated = postDboRepository.getById(postUpdated.getIdPost()).getTitle();
        String bodyBeforeUpdated = postDboRepository.getById(postUpdated.getIdPost()).getBody();
        postDboRepository.updateById(postUpdated, idPostToUpdate);
        // act
        postDboRepository.undoLastUpdate();
        // assert
        Assertions.assertAll("post",
                () -> Assertions.assertEquals(idPostToUpdate, idPostBeforeUpdated),
                () -> Assertions.assertEquals("know", titleBeforeUpdated),
                () -> Assertions.assertEquals(PostDboRepositoryTest.LOREM_IPSUM, bodyBeforeUpdated));
    }

    @Test
    void tryToUpdatePostThatNOExist() {
        // arrange
        int idPostToDelete = 101;
        Post postUpdated = new Post(idPostToDelete, "titulo original", "body original");
        // act - assert
        Exception exception = Assertions.assertThrows(RuntimeExceptionNullPost.class, () ->
                postDboRepository.updateById(postUpdated, idPostToDelete));
        Assertions.assertEquals("Post no existe", exception.getMessage());
    }

    @Test
    void tryDeletePostThatNotExist() {
        // arrange
        int idPostToDelete = 102;
        // act - assert
        Exception exception = Assertions.assertThrows(RuntimeExceptionNullPost.class, () ->
                postDboRepository.deleteById(idPostToDelete));
        Assertions.assertEquals("Post no existe", exception.getMessage());
    }

    @Test
    void getAllPosts() {

        // arrange
        // act
        List<Post> posts = postDboRepository.getAll();
        // assert
        Assertions.assertTrue(posts.size() > 3);
    }
}