package com.example.demo.domain;

import com.example.demo.application.fabric.CommandPost;
import com.example.demo.common.domain.exceptions.RuntimeExceptionNullValue;
import com.example.demo.common.domain.exceptions.RuntimeExceptionValue;
import com.example.demo.domain.builder.PostTestBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PostTest {

    @Test
    void createNewPostOk() {
        // arrange
        CommandPost commandPost = new PostTestBuilder().build();
        // act
        Post post = new Post(commandPost.getIdPost(), commandPost.getTitle(), commandPost.getBody());
        // assert
        assertAll("Post",
                () -> assertEquals(commandPost.getIdPost(), post.getIdPost()),
                () -> assertEquals(commandPost.getTitle(), post.getTitle()),
                () -> assertEquals(commandPost.getBody(), post.getBody()));
    }

    @Test
    void tryToCreateNewPostWithIdPostNoValid() {
        // arrange
        CommandPost commandPost = new PostTestBuilder().withPostId(0).build();
        //act - assert
        RuntimeExceptionValue thrown = Assertions.assertThrows(RuntimeExceptionValue.class, () -> {
            new Post(commandPost.getIdPost(), commandPost.getTitle(), commandPost.getBody());
        }, "idPost debe ser mayor que 0");
    }

    @Test
    void tryToCreateNewPostWithTitleNull() {
        // arrange
        CommandPost commandPost = new PostTestBuilder()
                .withPostId(100)
                .withTitle(null)
                .build();
        //act - assert
        RuntimeExceptionNullValue thrown = Assertions.assertThrows(RuntimeExceptionNullValue.class, () -> {
            new Post(commandPost.getIdPost(), commandPost.getTitle(), commandPost.getBody());
        }, "El titulo no puede ser nulo");
    }

    @Test
    void tryToCreateNewPostWithBodyNull() {
        // arrange
        CommandPost commandPost = new PostTestBuilder()
                .withPostId(100)
                .withBody(null)
                .build();
        //act - assert
        RuntimeExceptionNullValue thrown = Assertions.assertThrows(RuntimeExceptionNullValue.class, () -> {
            new Post(commandPost.getIdPost(), commandPost.getTitle(), commandPost.getBody());
        }, "El cuerpo del post no puede ser nulo");
    }
}



