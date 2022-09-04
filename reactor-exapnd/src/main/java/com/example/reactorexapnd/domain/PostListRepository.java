package com.example.reactorexapnd.domain;

import reactor.core.publisher.Mono;

public interface PostListRepository {

    void addPostList(PostList postList);

    Mono<PostList> findAllPostsByUserId(long userId, Long pageId);

}
