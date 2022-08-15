package com.example.demo.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {

    private final DatabaseClient databaseClient;

    @Override
    public Flux<Post> searchByKeyword(String keyword) {
        var containKeywordSQL = """
                    SELECT *
                    FROM post p
                    WHERE p.title LIKE CONCAT('%', :keyword, '%') OR
                        p.content LIKE CONCAT('%', :keyword, '%')
                """;

        return databaseClient.sql(containKeywordSQL)
                       .bind("keyword", keyword)
                       .fetch().all()
                       .map(row -> Post.builder()
                                           .id((Long) row.get("id"))
                                           .title((String) row.get("title"))
                                           .content((String) row.get("content"))
                                           .createdAt((LocalDateTime) row.get("created_at"))
                                           .updatedAt((LocalDateTime) row.get("updated_at"))
                                           .build());
    }

    @Override
    public Flux<PostUserSpecificInfo> findAllWithAuthor() {
        var sqlJoinUser = """
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
                """;

        return databaseClient.sql(sqlJoinUser)
                       .fetch().all()
                       .map(row -> PostUserSpecificInfo.builder()
                                           .postId((Long) row.get("postId"))
                                           .title((String) row.get("postTitle"))
                                           .content((String) row.get("postContent"))
                                           .postCreatedAt((LocalDateTime) row.get("postCreatedAt"))
                                           .authorId((Long) row.get("authorId"))
                                           .authorName((String) row.get("authorName"))
                                           .authorAge((Integer) row.get("authorAge"))
                                           .build());
    }

}
