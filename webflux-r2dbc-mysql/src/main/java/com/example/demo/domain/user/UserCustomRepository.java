package com.example.demo.domain.user;

import reactor.core.publisher.Flux;

public interface UserCustomRepository {

    Flux<User> findAllWithPosts();

}
