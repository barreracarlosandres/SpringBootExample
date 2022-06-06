package com.example.demo.services;

import com.example.demo.entity.Post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostRespository implements DaoPost {
    /**
     * Method use for initializer the Posts
     */
    private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    private List<Post> posts = new ArrayList<>(
            Arrays.asList(
                    new Post(1, "accounts", getDemoText()),
                    new Post(2, "chevy", getDemoText()),
                    new Post(3, "cooking", getDemoText()),
                    new Post(4, "not", getDemoText()),
                    new Post(5, "know", getDemoText())));

    /**
     * Function to get all Posts
     *
     * @return List of Posts
     */
    @Override
    public List<Post> getPosts() {
        //return posts;
        return new ArrayList<>(this.posts);  // Do that to make inmutable the List
    }

    @Override
    public boolean addPost(Post post) {
        return this.posts.add(post);
    }

    @Override
    public Post getPostById(int idPost) {
        Post salidaPost = null;
        for (Post post: posts) {
            if(post.getIdPost() == idPost) {
                salidaPost = post;
                break;
            }
        }
        return salidaPost;
    }

    @Override
    public boolean deletePostById(int idPost){
        boolean isDeleted = false;
        for (int i = 0; i < posts.size() ; i++) {
            Post tempPost = posts.get(i);
            if(tempPost.getIdPost() == idPost){
                posts.remove(i);
                isDeleted = true;
                break;
            }
        }
        return isDeleted;
    }

    @Override
    public boolean updatePostById(Post post, int idPost){
        boolean isUpdated = false;
        for (int i = 0; i < posts.size() ; i++) {
            Post tempPost = posts.get(i);
            if(tempPost.getIdPost() == idPost){
                posts.set(i, post);
                isUpdated = true;
                break;
            }
        }
        return isUpdated;
    }

    /**
     * @return The Lorem Ipsum text
     */
    private static String getDemoText() {
        return PostRespository.LOREM_IPSUM;
    }
}