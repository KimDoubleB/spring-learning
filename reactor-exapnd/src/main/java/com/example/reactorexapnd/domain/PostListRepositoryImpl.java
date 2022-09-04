package com.example.reactorexapnd.domain;

import lombok.Getter;
import reactor.core.publisher.Mono;

import java.util.concurrent.ConcurrentHashMap;

@Getter
public class PostListRepositoryImpl implements PostListRepository {

    private final ConcurrentHashMap<Long, PostList> postListByPageId;

    public PostListRepositoryImpl() {
        this.postListByPageId = new ConcurrentHashMap<>();
    }

    @Override
    public void addPostList(PostList postList) {
        postListByPageId.put(postList.postPageId(), postList);
    }

    @Override
    public Mono<PostList> findAllPostsByUserId(long userId, Long pageId) {
        var postList = getPostList(pageId);
        var posts = postList.posts().stream()
                            .filter(post -> post.userId() == userId)
                            .toList();
        return Mono.just(new PostList(postList.postPageId(), posts, postList.nextPageId()));
    }

    private PostList getPostList(Long pageId) {
        PostList postList;
        if (pageId == null) {
            postList = postListByPageId.get(1L); // Assume first element
        } else {
            postList = postListByPageId.get(pageId);
        }
        return postList;
    }

}
