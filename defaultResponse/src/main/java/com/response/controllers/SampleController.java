package com.response.controllers;

import com.response.dto.ResponseDto;
import com.response.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class SampleController {

    List<UserDto> users = new ArrayList<>(Arrays.asList(
            new UserDto("1", "bb", 27),
            new UserDto("2", "yj", 21))
    );

    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseDto> findUserById(@PathVariable String id) {
        UserDto user = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        return ResponseEntity.ok(ResponseDto.success()
                .init()
                .add("user", user));
    }


    @GetMapping("/user")
    public ResponseEntity<ResponseDto> findUsers() {
        return ResponseEntity.ok(ResponseDto.success()
                .init()
                .add("users", users));
    }
}
