package com.example.demo.domain.post.converter;

import com.example.demo.domain.post.Post;
import com.example.demo.domain.user.User;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.LocalDateTime;

@ReadingConverter
public class PostReadConverter implements Converter<Row, Post> {

    @Override
    public Post convert(Row source) {
        var user = User.builder()
                           .id((Long) source.get("authorId"))
                           .name((String) source.get("authorName"))
                           .age((Integer) source.get("authorAge"))
                           .build();
        return Post.builder()
                       .id((Long) source.get("postId"))
                       .title((String) source.get("postTitle"))
                       .content((String) source.get("postContent"))
                       .createdAt((LocalDateTime) source.get("postCreatedAt"))
                       .authorId(((Long) source.get("authorId")))
                       .author(user)
                       .build();
    }

}
