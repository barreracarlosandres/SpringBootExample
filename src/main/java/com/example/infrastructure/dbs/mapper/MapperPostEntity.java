package com.example.infrastructure.dbs.mapper;

import com.example.domain.Post;
import com.example.infrastructure.dbs.entity.PostEntity;

import java.util.List;
import java.util.stream.Collectors;

public class MapperPostEntity {

//    TODO se puede pasar a mapper annotation

    public PostEntity toDdo(Post post) {
        return new PostEntity(post.getIdPost(), post.getTitle(), post.getBody());
    }

    public List<Post> toDomain(List<PostEntity> postEntity) {
        return postEntity.stream().map(this::toDomain).collect(Collectors.toList());
    }

    public Post toDomain(PostEntity postEntity) {
        return new Post(postEntity.getIdPost(), postEntity.getTitle(), postEntity.getBody());
    }
}
