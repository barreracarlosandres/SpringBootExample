package com.example.demo.infrastructure.db.respository;

import com.example.demo.infrastructure.db.dbo.PostEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class DBArrayPosts {

    private static DBArrayPosts dbArrayPosts;

    private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    private List<PostEntity> postsList = new ArrayList<>(
            Arrays.asList(
                    new PostEntity(1, "accounts", getDemoText()),
                    new PostEntity(2, "chevy", getDemoText()),
                    new PostEntity(3, "cooking", getDemoText()),
                    new PostEntity(4, "not", getDemoText()),
                    new PostEntity(5, "know", getDemoText())));

    private DBArrayPosts() {
    }

    static synchronized DBArrayPosts getInstance() {
        if (dbArrayPosts == null) {
            dbArrayPosts = new DBArrayPosts();
        }
        return dbArrayPosts;
    }

    public List<PostEntity> getAllPosts() {
        return new ArrayList<PostEntity>(postsList);        // Did it to get inmutable List
    }

    /**
     * @return The Lorem Ipsum text
     */
    private static String getDemoText() {
        return DBArrayPosts.LOREM_IPSUM;
    }

    public int size() {
        return postsList.size();
    }

    public boolean add(PostEntity newPostEntityToAdd) {
        return postsList.add(newPostEntityToAdd);
    }

    public PostEntity get(int postId) {
        return postsList.get(postId);
    }

    public void remove(int i) {
        postsList.remove(i);
    }

    public void set(int postId, PostEntity toDdo) {
        postsList.set(postId, toDdo);
    }
}
