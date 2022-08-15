package com.example.demo.application.dto;

import com.example.demo.domain.post.Post;

public record SavePostRequest(Long authorId, String title, String content) {

    public Post toEntity() {
        return new Post(title, content, authorId);
    }

}
