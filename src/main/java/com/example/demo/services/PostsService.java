package com.example.demo.services;

import com.example.demo.entity.Post;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PostsService{

    private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    /**
     * Method use for initializer the Post
     */
    static private List<Post> posts = new ArrayList<>(Arrays.asList(
            new Post(1, "accounts", getDemoText()),
            new Post(2, "chevy", getDemoText()),
            new Post(3, "cooking", getDemoText()),
            new Post(4, "not", getDemoText()),
            new Post(5, "know", getDemoText())
    ));

    /**
     * Function to get all Posts
     *
     * @return List of Posts
     */
    public List<Post> getPosts(){
        return posts;
    }

    /**
     * Function to get the post by id
     *
     * @param id_post the id, unique, that represent the Post
     * @return Post found in the array
     */
    public Post getPost(int id_post){
        for (Post post: posts) {
            if(post.getPostId() == id_post)
                return post;
        }
        return null;
    }

    /**
     * Funtion to add Post to the Array
     * @param listElement object that represent the Post Object to be added
     */
    public void addPost(Post listElement) {
        posts.add(listElement);
    }

    /**
     * Function to update Post
     * @param post Object that correspond to the new data of Post to be updated
     * @param id_post id that represent Post to be updated
     */
    public void updatePost(Post post, int id_post) {
        for (int i = 0; i < posts.size() ; i++) {
            Post tempPost = posts.get(i);
            if(tempPost.getPostId() == id_post){
                posts.set(i, post);
                return;
            }
        }
    }

    /**
     * Function for delete the Post
     *
     * @param id_post id that represent the Post to be deleted
     */
    public void deletePost(int id_post) {
        for (int i = 0; i < posts.size() ; i++) {
            Post tempPost = posts.get(i);
            if(tempPost.getPostId() == id_post){
                posts.remove(i);
                return;
            }
        }
    }

    /**
     * @return The Lorem Ipsum text
     */
    private static String getDemoText() {
        return LOREM_IPSUM;
    }


}
