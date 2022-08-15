package com.example.demo.controller;

import com.example.demo.application.PostService;
import com.example.demo.application.dto.PostResponse;
import com.example.demo.application.dto.PostResponseWithUser;
import com.example.demo.application.dto.SavePostRequest;
import com.example.demo.domain.post.Post;
import com.example.demo.domain.post.PostRepository;
import com.example.demo.domain.post.PostUserSpecificInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    @GetMapping
    public Mono<ResponseEntity<List<PostResponse>>> getAllPosts() {
        return postService.getAll().collectList()
                       .map(ResponseEntity::ok);
    }

    @GetMapping("/{postId}")
    public Mono<ResponseEntity<PostResponse>> getAllPosts(@PathVariable Long postId) {
        return postService.getOne(postId)
                       .map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> savePost(@RequestBody Mono<SavePostRequest> request) {
        return request.flatMap(postService::save)
                       .thenReturn(ResponseEntity.created(URI.create("/")).build());
    }

    @GetMapping("/search")
    public Mono<ResponseEntity<List<Post>>> searchKeyword(@RequestParam String keyword) {
        return postRepository.searchByKeyword(keyword).collectList()
                       .map(ResponseEntity::ok);
    }

    @GetMapping("/specific")
    public Mono<ResponseEntity<List<PostUserSpecificInfo>>> getAllSpecificPosts() {
        return postRepository.findAllWithAuthor().collectList()
                       .map(ResponseEntity::ok);
    }

    @GetMapping("/specific2")
    public Mono<ResponseEntity<List<PostResponseWithUser>>> getAllSpecificPosts2() {
        return postRepository.findAllWithAuthorUsingQuery()
                       .map(PostResponseWithUser::from).collectList()
                       .map(ResponseEntity::ok);
    }

}
