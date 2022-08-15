package com.example.demo.domain.post;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface PostRepository extends R2dbcRepository<Post, Long>, PostCustomRepository {

    Flux<Post> findByTitleContains(String keyword);

    @Query("""
                SELECT  p.id as postId,
                        p.title as postTitle,
                        p.content as postContent,
                        p.created_at as postCreatedAt,
                        u.id as authorId, 
                        u.name as authorName, 
                        u.age as authorAge 
                FROM post p
                INNER JOIN user u
                ON p.author_id = u.id
            """)
    Flux<Post> findAllWithAuthorUsingQuery();

}
