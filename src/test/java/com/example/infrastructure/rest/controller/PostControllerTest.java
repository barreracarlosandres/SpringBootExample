package com.example.infrastructure.rest.controller;

import com.example.application.fabric.CommandPost;
import com.example.common.infrastructure.exception.PostMessageExeptions;
import com.example.infrastructure.builder.PostControllerTestBuilder;
import com.example.infrastructure.configuration.ConfigurationApp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PostController.class)                               // Controller to test
@ContextConfiguration(classes = ConfigurationApp.class)         // Bean configuration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PostControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    void getAllPosts() throws Exception {
        mocMvc.perform(get("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idPost", is(1)))
                .andExpect(jsonPath("$[1].idPost", is(2)));
    }

    @Test
    void getPostByIdThatExits() throws Exception {

        // arrange
        int idPost = 2;
        // act - assert
        mocMvc.perform(post("/posts/{id}", idPost)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("idPost", is(idPost)))
                .andExpect(jsonPath("title", is("chevy")));
    }

    @Test
    void insertPostOk() throws Exception {
        //arrange
        CommandPost post = new PostControllerTestBuilder().withPostId(11).withTitle("New Tittle").build();
        //act - assert
        this.postToInsertOk(post);
    }

    @Test
    void tryToInsertPostThatExits() throws Exception {
        //arrange
        CommandPost post = new PostControllerTestBuilder()
                .withPostId(1)
                .build();
        //act - assert
        mocMvc.perform(post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isAlreadyReported())
                .andExpect(jsonPath("message", Is.is(PostMessageExeptions.YA_EXISTE_EL_POST)));
    }

    @Test
    void tryTInsertBadPostWithIdPostCero() throws Exception {
        //arrange
        CommandPost post = new PostControllerTestBuilder().withPostId(0).build();
        //act - assert
        mocMvc.perform(post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", is(PostMessageExeptions.ID_POST_DEBE_SER_MAYOR_QUE_0)));
    }

    @Test
    void updatePostByIdThatExist() throws Exception {
        //arrange
        CommandPost post = new PostControllerTestBuilder().withPostId(1).withTitle("tittle modified").build();
        // act - Assert
        putToUpdatePostAndCheckOk(post);
        // assert
    }

    @Test
    void tryToUpdatePostByTitleNull() throws Exception {
        //arrange
        CommandPost post = new PostControllerTestBuilder().withPostId(100).withTitle(null).build();
        //act - assert
        mocMvc.perform(put("/posts/{idPost}", post.getIdPost())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", is(PostMessageExeptions.EL_TITULO_NO_PUEDE_SER_NULO)));
    }

    @Test
    void deletePostByIdThatExits() throws Exception {
        // arrange
        CommandPost post = new PostControllerTestBuilder().withPostId(4).withTitle("Prueba add").build();
        // act - assert
//        this.deletePostByIdPost(post);
        mocMvc.perform(delete("/posts/{idPost}", post.getIdPost())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void undoUpdatePostOk() throws Exception {
        // Arrange
        CommandPost post = new PostControllerTestBuilder().withPostId(6).withTitle("Prueba add").build();
        this.putToUpdatePostAndCheckOk(post);
        // Act - Assert
        mocMvc.perform(put("/posts/undoUpdated")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void undoDeletePostOk() throws Exception {
        // Arrange
        CommandPost post = new PostControllerTestBuilder().withPostId(7).build();
//        this.postToAddNewPostOk(post);
        this.deletePostByIdPost(post);
        // Act - Assert
        mocMvc.perform(put("/posts/undoDeleted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void undoInsertPostOk() throws Exception {
        // Arrange
        CommandPost post = new PostControllerTestBuilder().withPostId(15).build();
        this.postToInsertOk(post);
        // Act - Assert
        mocMvc.perform(put("/posts/undoAdded")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private void checkOkIdPostAndTitleOfPostById(CommandPost post, String title) throws Exception {
        mocMvc.perform(get("/posts/{idPost}", post.getIdPost())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("idPost", is(post.getIdPost())))
                .andExpect(jsonPath("title", is(title)));
    }

    private void checkPostByIDThatNotExist(CommandPost post) throws Exception {
        mocMvc.perform(get("/posts/{idPost}", post.getIdPost())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private void putToUpdatePostAndCheckOk(CommandPost post) throws Exception {
        mocMvc.perform(put("/posts/{idPost}", post.getIdPost())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isOk());
    }

    private void deletePostByIdPost(CommandPost post) throws Exception {
        mocMvc.perform(delete("/posts/{idPost}", post.getIdPost())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private void postToInsertOk(CommandPost post) throws Exception {
        mocMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isCreated());
    }
}