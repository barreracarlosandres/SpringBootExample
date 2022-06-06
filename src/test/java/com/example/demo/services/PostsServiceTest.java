package com.example.demo.services;


import com.example.demo.controller.PostController;
import com.example.demo.entity.Post;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
class PostsServiceTest {

    @Test
    void getPosts() {

        //arrange
        PostController postController = initialPostController();
        int[] idPosts = {1, 2, 3, 4, 5};
        //act
        List<Post> postList = postController.getPosts();
        //assert
        assertAll("idPosts",
                () -> assertEquals(idPosts[0], postList.get(0).getPostId()),
                () -> assertEquals(idPosts[1], postList.get(1).getPostId()),
                () -> assertEquals(idPosts[2], postList.get(2).getPostId()),
                () -> assertEquals(idPosts[3], postList.get(3).getPostId()),
                () -> assertEquals(idPosts[4], postList.get(4).getPostId()) );
    }

    @Test
    void getPostById() {
        //arrange
        PostController postController = initialPostController();
        //act
        //assert
        assertEquals(1, postController.getPostById(1).getPostId() );
    }

    @Test
    void updateTheTitleOfPost() {
        //arrange
        Post post = new PostTestBuilder()
                .withPostId(1)
                .withTitle("Prueba")
                .build();
        PostController postController = initialPostController();
        //act
        postController.updatePost(post, 1);
        //assert
        assertEquals("Prueba", postController.getPostById(1).getTitle() );
    }

    @Test
    void updateTheBudyOfPost() {
        //arrange
        Post post = new PostTestBuilder()
                .withPostId(1)
                .withBody("New body text")
                .build();
        PostController postController = initialPostController();
        //act
        postController.updatePost(post, 1);
        //assert
        assertEquals("New body text", postController.getPostById(1).getBody() );
    }

    /**
     * Use order just proper order of this test with id=10
     *  firsts search Post id=10 and get null (don´t have Posts whit id = 10)
     *  second insert Post wit id = 10 (firsts add Post with id = 10)
     *  third delete Post with id = 10 (second method insert the Post with id=10)
     */

    @Test
    @Order(1)
    void getPostByIdNotFoundReturnNull() {
        //arrange
        PostController postController = initialPostController();
        //act
        //assert
        assertNull( postController.getPostById(10) );
    }

    @Test
    @Order(2)
    void addNewPost() {
        //arrange
        Post post = new PostTestBuilder()
                .withPostId(10)
                .build();
        PostController postController = initialPostController();
        //act
        postController.addPost(post);
        //assert
        assertEquals(10, postController.getPostById(10).getPostId() );
    }

    @Test
    @Order(3)
    void deletePost() {
        Post post = new PostTestBuilder()
                .withPostId(1)
                .withTitle("Prueba")
                .build();
        PostController postController = initialPostController();
        //act
        postController.deletePost(10);
        //assert
        assertNull(postController.getPostById(10) );
    }

    private PostController initialPostController() {
        PostsService postsService = new PostsService();
        PostController postController = new PostController(postsService);
        return postController;
    }
}