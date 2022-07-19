package com.example.infrastructure.rest.mapper;

import com.example.domain.Post;
import com.example.infrastructure.rest.dto.PostDto;

import java.util.List;
import java.util.stream.Collectors;

public class MapperPost {

//    TODO se puede pasar a mapper annotation

    public PostDto toDto(Post post){
        return new PostDto(post.getIdPost(), post.getTitle(), post.getBody());
    }

    public List<PostDto> toDto(List<Post> post){
        return post.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Post toDomain(PostDto postDto) {
        return new Post(postDto.getIdPost(), postDto.getTitle(), postDto.getBody());
    }
}
