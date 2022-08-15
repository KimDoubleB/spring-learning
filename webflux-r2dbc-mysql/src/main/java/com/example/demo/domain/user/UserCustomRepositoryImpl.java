package com.example.demo.domain.user;

import com.example.demo.domain.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.Comparator;

@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private static final String USER_ID_FIELD_NAME = "userId";

    private final DatabaseClient databaseClient;

    @Override
    public Flux<User> findAllWithPosts() {
        var sqlWithPost = """
                SELECT 
                    u.id as userId, u.name as userName, u.age as userAge, 
                    u.created_at as userCreatedAt, u.updated_at as userUpdatedAt,
                    p.id as postId, p.title as postTitle, p.content as postContent, 
                    p.created_at as postCreatedAt, p.updated_at as postUpdatedAt
                FROM user u
                INNER JOIN post p
                ON u.id = p.author_id
                """;

        return databaseClient.sql(sqlWithPost)
                       .fetch().all()
                       .sort(Comparator.comparing(result -> (Long) result.get(USER_ID_FIELD_NAME)))
                       .bufferUntilChanged(result -> result.get(USER_ID_FIELD_NAME))
                       .map(result -> {
                           var posts = result.stream()
                                               .map(row -> Post.builder()
                                                                   .id((Long) row.get("postId"))
                                                                   .title((String) row.get("postTitle"))
                                                                   .content((String) row.get("postContent"))
                                                                   .createdAt((LocalDateTime) row.get("postCreatedAt"))
                                                                   .updatedAt((LocalDateTime) row.get("postCreatedAt"))
                                                                   .build())
                                               .toList();
                           var row = result.get(0);
                           return User.builder()
                                          .id((Long) row.get(USER_ID_FIELD_NAME))
                                          .name((String) row.get("userName"))
                                          .age((Integer) row.get("userAge"))
                                          .createdAt((LocalDateTime) row.get("userCreatedAt"))
                                          .updatedAt((LocalDateTime) row.get("userUpdatedAt"))
                                          .posts(posts)
                                          .build();
                       });
    }

}
