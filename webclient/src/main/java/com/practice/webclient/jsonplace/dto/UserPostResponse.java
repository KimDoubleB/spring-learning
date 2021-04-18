package com.practice.webclient.jsonplace.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserPostResponse implements JsonPlaceResponse {
    private Long userId;
    private Long postId;
    private String title;
    private String body;
}
