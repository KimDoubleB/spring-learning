package com.example.reactorexapnd;

import com.example.reactorexapnd.domain.Post;
import com.example.reactorexapnd.domain.PostList;
import com.example.reactorexapnd.domain.PostListRepository;
import com.example.reactorexapnd.domain.PostListRepositoryImpl;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ReactorExapndTest {

    @Test
    void test() {
        // init
        PostListRepository postListRepository = new PostListRepositoryImpl();
        dataInitialization(postListRepository);

        // expand test
        var userId = 1L;
        var allPostsByUserId = postListRepository.findAllPostsByUserId(userId, null)
                                       .expand(postList -> {
                                           var nextPageId = postList.nextPageId();
                                           if (nextPageId == null) {
                                               return Mono.empty();
                                           }
                                           return postListRepository.findAllPostsByUserId(userId, nextPageId);
                                       })
                                       .flatMap(postList -> Flux.fromIterable(postList.posts()))
                                       .log();

        StepVerifier.create(allPostsByUserId)
                .assertNext(post -> assertThat(post).isEqualTo(new Post(1L, 1L, "1 Content")))
                .assertNext(post -> assertThat(post).isEqualTo(new Post(3L, 1L, "3 Content")))
                .assertNext(post -> assertThat(post).isEqualTo(new Post(7L, 1L, "7 Content")))
                .assertNext(post -> assertThat(post).isEqualTo(new Post(10L, 1L, "10 Content")))
                .assertNext(post -> assertThat(post).isEqualTo(new Post(11L, 1L, "11 Content")))
                .assertNext(post -> assertThat(post).isEqualTo(new Post(12L, 1L, "12 Content")))
                .verifyComplete();
    }

    private void dataInitialization(PostListRepository postListRepository) {
        var firstPostList = new PostList(1L,
                List.of(
                        new Post(1L, 1L, "1 Content"),      // THIS
                        new Post(2L, 2L, "2 Content"),
                        new Post(3L, 1L, "3 Content")),     // THIS
                2L);
        postListRepository.addPostList(firstPostList);
        var secondPostList = new PostList(2L,
                List.of(
                        new Post(4L, 4L, "4 Content"),
                        new Post(5L, 2L, "5 Content"),
                        new Post(6L, 3L, "6 Content")),
                3L);
        postListRepository.addPostList(secondPostList);
        var thirdPostList = new PostList(3L,
                List.of(
                        new Post(7L, 1L, "7 Content"),      // THIS
                        new Post(8L, 2L, "8 Content"),
                        new Post(9L, 3L, "9 Content")),
                4L);
        postListRepository.addPostList(thirdPostList);
        var lastPostList = new PostList(4L,
                List.of(
                        new Post(10L, 1L, "10 Content"),    // THIS
                        new Post(11L, 1L, "11 Content"),    // THIS
                        new Post(12L, 1L, "12 Content")),   // THIS
                null);
        postListRepository.addPostList(lastPostList);
    }

}
