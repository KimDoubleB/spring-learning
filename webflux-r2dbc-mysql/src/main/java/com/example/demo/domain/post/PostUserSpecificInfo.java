package com.example.demo.domain.post;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostUserSpecificInfo(Long postId, String title, String content, LocalDateTime postCreatedAt,
                                   Long authorId, String authorName, Integer authorAge) {
}
