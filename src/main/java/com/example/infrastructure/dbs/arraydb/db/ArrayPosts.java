package com.example.infrastructure.dbs.arraydb.db;

import com.example.infrastructure.dbs.arraydb.entity.PostEntityArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ArrayPosts {

    private static ArrayPosts arrayPosts;

    private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    private List<PostEntityArray> postsList = new ArrayList<>(
            Arrays.asList(
                    new PostEntityArray(1, "accounts", getDemoText()),
                    new PostEntityArray(2, "chevy", getDemoText()),
                    new PostEntityArray(3, "cooking", getDemoText()),
                    new PostEntityArray(4, "not", getDemoText()),
                    new PostEntityArray(5, "know", getDemoText()),
                    new PostEntityArray(6, "know", getDemoText()),
                    new PostEntityArray(7, "know", getDemoText())));

    private ArrayPosts() {
    }

    public static synchronized ArrayPosts getInstance() {
        if (arrayPosts == null) {
            arrayPosts = new ArrayPosts();
        }
        return arrayPosts;
    }

    public List<PostEntityArray> getAllPosts() {
        return new ArrayList<>(postsList);        // Did it to get inmutable List
    }

    private static String getDemoText() {
        return ArrayPosts.LOREM_IPSUM;
    }

    public int size() {
        return postsList.size();
    }

    public boolean insert(PostEntityArray newPostEntityArrayToInsert) {
        return postsList.add(newPostEntityArrayToInsert);
    }

    public PostEntityArray get(int postIdToReturn) {
        return postsList.get(postIdToReturn);
    }

    public void remove(int idPostToRemove) {
        postsList.remove(idPostToRemove);
    }

    public void update(int postId, PostEntityArray postUpdated) {
        postsList.set(postId, postUpdated);
    }
}
