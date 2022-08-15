package com.example.demo.application;

import com.example.demo.application.dto.UserResponse;
import com.example.demo.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Mono<List<UserResponse>> getAll() {
        return userRepository.findAllWithPosts()
                       .map(UserResponse::from)
                       .collectList();
    }

}
