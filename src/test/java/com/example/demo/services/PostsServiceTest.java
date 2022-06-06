package com.example.demo.services;


import com.example.demo.PostTestBuilder;
import com.example.demo.entity.Post;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(OrderAnnotation.class)
class PostsServiceTest {

    private List<Post> postList = new ArrayList<Post>(Arrays.asList(
            new Post(1, "accounts", "Lorem ipsum dolor sit amet"),
            new Post(2, "chevy", "Lorem ipsum dolor sit amet")));

    @Test
    void getAllPosts(){
        //arrange
        PostsService postsService = prepareMockPostData();
        //act
        List<Post> listPosts = postsService.getPosts();
        //assert
        assertAll("idPosts",
                () -> assertEquals(listPosts.get(0).getIdPost(), 1),
                () -> assertEquals(listPosts.get(1).getIdPost(), 2)
                );
    }

    @Test
    void getPostByIdThatNotExist(){
        //arrange
        PostRespository postRespository = Mockito.mock(PostRespository.class);
        Mockito.when(postRespository.getPostById(Mockito.anyInt())).thenReturn(null);
        PostsService postsService = new PostsService(postRespository);
        //act
        Post post = postsService.getPostById(15);
        //assert
        assertEquals(null, post);
    }

    @Test
    void getTitlePostByIdThatExist(){
        //arrange
        Post mockPost = new PostTestBuilder()
                .withPostId(1)
                .withTitle("Prueba")
                .build();
        PostRespository postRespository = Mockito.mock(PostRespository.class);
        Mockito.when(postRespository.getPostById(Mockito.anyInt())).thenReturn(mockPost);
        PostsService postsService = new PostsService(postRespository);
        //act
        Post post = postsService.getPostById(1);
        //assert
        assertEquals("Prueba", post.getTitle());
    }

    @Test
    void getBuddyPostByIdThatExist(){
        //arrange
        Post mockPost = new PostTestBuilder()
                .withPostId(1)
                .withBody("Buddy")
                .build();
        PostRespository postRespository = Mockito.mock(PostRespository.class);
        Mockito.when(postRespository.getPostById(Mockito.anyInt())).thenReturn(mockPost);
        PostsService postsService = new PostsService(postRespository);
        //act
        Post post = postsService.getPostById(1);
        //assert
        assertEquals("Buddy", post.getBody());
    }

    @Test
    void updatePostByIdThatExist() {
        //arrange
        Post mockPost = new PostTestBuilder()
                .withPostId(1)
                .withTitle("Prueba")
                .build();
        PostRespository postRespository = Mockito.mock(PostRespository.class);
        Mockito.when(postRespository.updatePostById(Mockito.any(Post.class) ,Mockito.anyInt())).thenReturn(true);
        PostsService postsService = new PostsService(postRespository);
        //act - assert
        assertEquals(true, postsService.updatePostById(mockPost,1));
    }

    @Test
    void updatePostByIdThatNotExist() {
        //arrange
        Post mockPost = new PostTestBuilder()
                .withPostId(1)
                .withTitle("Prueba")
                .build();
        PostRespository postRespository = Mockito.mock(PostRespository.class);
        Mockito.when(postRespository.updatePostById(Mockito.any(Post.class) ,Mockito.anyInt())).thenReturn(false);
        PostsService postsService = new PostsService(postRespository);
        //act - assert
        assertEquals(false, postsService.updatePostById(mockPost,1));
    }

    @Test
    void deletePostThatExits()
    {
        //arrange
        PostRespository postRespository = Mockito.mock(PostRespository.class);
        Mockito.when(postRespository.deletePostById(Mockito.anyInt())).thenReturn(true);
        PostsService postsService = new PostsService(postRespository);
        //act - assert
        assertEquals(true, postsService.deletePostById(1));
    }

    @Test
    void deletePostThatNotExits() {
        //arrange
        PostRespository postRespository = Mockito.mock(PostRespository.class);
        Mockito.when(postRespository.deletePostById(Mockito.anyInt())).thenReturn(false);
        PostsService postsService = new PostsService(postRespository);
        //act - assert
        assertEquals(false, postsService.deletePostById(1));
    }

    @Test
    void addPostToRepository() {
        //arrange
        Post mockPost = new PostTestBuilder()
                .build();
        PostRespository postRespository = Mockito.mock(PostRespository.class);
        Mockito.when(postRespository.addPost(Mockito.any(Post.class))).thenReturn(true);
        PostsService postsService = new PostsService(postRespository);
        //act - assert
        assertEquals(true, postsService.addPost(mockPost));
    }

    @Test
    void tryToAddPostToRepositoryAndFailed() {
        //arrange
        Post mockPost = new PostTestBuilder()
                .build();
        PostRespository postRespository = Mockito.mock(PostRespository.class);
        Mockito.when(postRespository.addPost(Mockito.any(Post.class))).thenReturn(false);
        PostsService postsService = new PostsService(postRespository);
        //act - assert
        assertEquals(false, postsService.addPost(mockPost));
    }

    private PostsService prepareMockPostData() {
        PostRespository postRespository = Mockito.mock(PostRespository.class);
        Mockito.when(postRespository.getPosts()).thenReturn(postList);
        PostsService postsService = new PostsService(postRespository);
        return postsService;
    }
}