package com.example.demo.application.dto;

import com.example.demo.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;

public record UserResponse(Long id, String name, Integer age, LocalDateTime createdAt, List<PostResponse> posts) {

    public static UserResponse from(User user) {
        var posts = user.getPosts().stream()
                            .map(PostResponse::from).toList();
        return new UserResponse(user.getId(),
                user.getName(),
                user.getAge(),
                user.getCreatedAt(),
                posts);
    }

}
