package com.example.reactorexapnd.domain;

import java.util.List;

public record PostList(Long postPageId, List<Post> posts, Long nextPageId) {}
