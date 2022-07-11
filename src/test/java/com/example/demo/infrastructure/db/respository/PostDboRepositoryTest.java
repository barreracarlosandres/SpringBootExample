package com.example.demo.infrastructure.db.respository;

import com.example.demo.common.infrastructure.exception.RuntimeExceptionNullPost;
import com.example.demo.domain.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;



class PostDboRepositoryTest {


    @Disabled("pending to implement")
    @Test
    void addPostAndThenUndo() {
        // arrange
        PostDboRepository postDboRepository = new PostDboRepository();
        Post post = new Post(100, "tittle", "body");
        postDboRepository.addPost(post);
        postDboRepository.undoAddedPost();
        // act - assert
        RuntimeExceptionNullPost thrown = Assertions.assertThrows(RuntimeExceptionNullPost.class, () -> {
            postDboRepository.getPostById(post.getIdPost());
        }, "Post no existe");

    }

//    @Disabled("pending to implement")
    @Test
    void deletePostByIdAndThenUndo() {
        // arrange
        PostDboRepository postDboRepository = new PostDboRepository();
        int idPostToDelete = 1;
        // act
        postDboRepository.deletePostById(idPostToDelete);
        postDboRepository.undoDeletedPost();
        // assert
        Assertions.assertTrue( postDboRepository.getPostById(idPostToDelete).getIdPost() == idPostToDelete );

    }

//    @Disabled("pending to implement")
    @Test
    void updateTitleOfPostAndThenUndo() {
        // arrange
        PostDboRepository postDboRepository = new PostDboRepository();
        Post postUpdated = new Post(1, "titulo original", "body original");
        // act
        postDboRepository.updatePostById(postUpdated, 1);
        postDboRepository.undoUpdateTitlePost();
        // assert
        Assertions.assertTrue( postDboRepository.getPostById(postUpdated.getIdPost()).getTitle() == "accounts" );

    }
}