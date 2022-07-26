package com.example.infrastructure.dbs.mapper;

import com.example.domain.Post;
import com.example.infrastructure.dbs.arraydb.entity.PostEntityArray;

import java.util.List;
import java.util.stream.Collectors;

public class MapperDdo {

//    TODO se puede pasar a mapper annotation

    public PostEntityArray toDdo(Post post) {
        return new PostEntityArray(post.getIdPost(), post.getTitle(), post.getBody());
    }

    public List<Post> toDomain(List<PostEntityArray> postEntityArray) {
        return postEntityArray.stream().map(this::toDomain).collect(Collectors.toList());
    }

    public Post toDomain(PostEntityArray postEntityArray) {
        return new Post(postEntityArray.getIdPost(), postEntityArray.getTitle(), postEntityArray.getBody());
    }
}
