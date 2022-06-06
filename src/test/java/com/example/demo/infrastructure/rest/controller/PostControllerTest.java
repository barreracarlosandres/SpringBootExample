package com.example.demo.infrastructure.rest.controller;

import com.example.demo.application.fabric.CommandPost;
import com.example.demo.infrastructure.builder.PostControllerTestBuilder;
import com.example.demo.infrastructure.configuration.ConfigurationApp;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.ir.annotations.Ignore;
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
    void tryToGetPostByIdThatNotExits() throws Exception {

        // arrange
        int idPost = 100;
        // act - assert
        mocMvc.perform(post("/posts/{id}", idPost)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void addNewPostOk() throws Exception {
        //arrange
        CommandPost post = new PostControllerTestBuilder()
                .withPostId(11)
                .withTitle("New Tittle")
                .build();
        //act - assert
        mocMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isCreated());
        // assert
        mocMvc.perform(get("/posts/{idPost}", post.getIdPost())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("idPost", is(post.getIdPost())))
                .andExpect(jsonPath("title", is("New Tittle")));
    }

    @Test
    void tryToAddPostThatExits() throws Exception {
        //arrange
        CommandPost post = new PostControllerTestBuilder()
                .withPostId(1)
                .build();
        //act - assert
        mocMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isAlreadyReported());
    }

    @Ignore
//    @Test
    void addNewBadPost() throws Exception {
        //arrange
        CommandPost post = new PostControllerTestBuilder()
                .withPostId(10)
                .withTitle("Prueba add")
                .build();
        //act - assert
        mocMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", is("Post ya registrado")));
//        FIXME validar si este test aplica o no

    }

    @Test
    void tryToAddNeBadPostWithIdPostCero() throws Exception {
        //arrange
        CommandPost post = new PostControllerTestBuilder()
                .withPostId(0)
                .build();
        //act - assert
        mocMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", is("idPost debe ser mayor que 0")));
    }

    @Test
    void updatePostByIdThatExist() throws Exception {
        //arrange
        CommandPost post = new PostControllerTestBuilder()
                .withPostId(1)
                .withTitle("tittle modified")
                .build();
        // act
        mocMvc.perform(put("/posts/{idPost}", post.getIdPost())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isOk());
        // assert
        mocMvc.perform(get("/posts/{idPost}", post.getIdPost())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("idPost", is(post.getIdPost())))
                .andExpect(jsonPath("title", is("tittle modified")));
    }

    @Test
    void tryToUpdatePostByIdThatNotExist() throws Exception {
        //arrange
        CommandPost post = new PostControllerTestBuilder()
                .withPostId(100)
                .withTitle("Prueba add")
                .build();
        //act - assert
        mocMvc.perform(put("/posts/{idPost}", post.getIdPost())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isNotFound())
        .andExpect(jsonPath("message", is("Post no existe")));
    }

    @Test
    void tryToUpdatePostByTitleNull() throws Exception {
        //arrange
        CommandPost post = new PostControllerTestBuilder()
                .withPostId(100)
                .withTitle(null)
                .build();
        //act - assert
        mocMvc.perform(put("/posts/{idPost}", post.getIdPost())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", is("El titulo no puede ser nulo")));
    }

    @Test
    void tryToUpdatePostByBodyNull() throws Exception {
        //arrange
        CommandPost post = new PostControllerTestBuilder()
                .withPostId(100)
                .withBody(null)
                .build();
        //act - assert
        mocMvc.perform(put("/posts/{idPost}", post.getIdPost())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", is("El cuerpo del post no puede ser nulo")));
    }

    @Test
    void deletePostByIdThatExits() throws Exception {
        // arrange
        CommandPost post = new PostControllerTestBuilder()
                .withPostId(1)
                .withTitle("Prueba add")
                .build();
        // act
        mocMvc.perform(delete("/posts/{idPost}", post.getIdPost())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // assert
        mocMvc.perform(delete("/posts/{idPost}", post.getIdPost())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletePostByIdThatNotExits() throws Exception {
        //arrange
        CommandPost post = new PostControllerTestBuilder()
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