package com.tistory.bbdevstory.springboot.service.posts;

import com.tistory.bbdevstory.springboot.domain.posts.Posts;
import com.tistory.bbdevstory.springboot.domain.posts.PostsRepository;
import com.tistory.bbdevstory.springboot.web.dto.PostsListResponseDto;
import com.tistory.bbdevstory.springboot.web.dto.PostsResponseDto;
import com.tistory.bbdevstory.springboot.web.dto.PostsSaveRequestDto;
import com.tistory.bbdevstory.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = getPostEntityById(id);
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public PostsResponseDto findById (Long id){
        Posts entity = getPostEntityById(id);
        return new PostsResponseDto(entity);
    }

    @Transactional
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = getPostEntityById(id);
        postsRepository.delete(posts);
    }

    private Posts getPostEntityById(Long id){
        return postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
    }
}
