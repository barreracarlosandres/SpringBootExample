package com.example.demo.controller;

import com.example.demo.PostTestBuilder;
import com.example.demo.configuration.ConfigurationApp;
import com.example.demo.entity.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@WebMvcTest(PostController.class)                           // Controller to test
@ContextConfiguration(classes= ConfigurationApp.class)      // Bean configuration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PostControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    void getPosts() throws Exception {
        mocMvc.perform(get("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idPost", is(1)))
                .andExpect(jsonPath("$[1].idPost", is(2)));
    }

    @Test
    void getPostById() throws Exception {

        // arrange
        int idPost = 1;
        // act - assert
        mocMvc.perform(post("/posts/{id}", idPost)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("idPost", is(idPost)))
                .andExpect(jsonPath("title", is("accounts")));
    }

    @Test
    void addNewOkPost() throws Exception {
        //arrange
        Post post = new PostTestBuilder()
                .withPostId(10)
                .withTitle("Prueba add")
                .build();
        //act - assert
        mocMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isOk());
    }

    @Test
    void addNewBadPost() throws Exception {
        //arrange
        Post post = new PostTestBuilder()
                .withPostId(10)
                .withTitle("Prueba add")
                .build();
        //act - assert
        mocMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updatePostByIdThatExist() throws Exception {
        //arrange
        Post post = new PostTestBuilder()
                .withPostId(1)
                .withTitle("Prueba add")
                .build();
        //act - assert
        mocMvc.perform(put("/posts/{idPost}", post.getIdPost())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isOk());
    }

    @Test
    void updatePostByIdThatNotExist() throws Exception {
        //arrange
        Post post = new PostTestBuilder()
                .withPostId(100)
                .withTitle("Prueba add")
                .build();
        //act - assert
        mocMvc.perform(put("/posts/{idPost}", post.getIdPost())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletePostByIdThatExits() throws Exception {
        //arrange
        Post post = new PostTestBuilder()
                .withPostId(1)
                .withTitle("Prueba add")
                .build();
        //act - assert
        mocMvc.perform(delete("/posts/{idPost}", post.getIdPost())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deletePostByIdThatNotExits() throws Exception {
        //arrange
        Post post = new PostTestBuilder()
                .withPostId(100)
                .withTitle("Prueba add")
                .build();
        //act - assert
        mocMvc.perform(delete("/posts/{idPost}", post.getIdPost())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}