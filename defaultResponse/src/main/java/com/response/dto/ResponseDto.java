package com.response.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {
    private final int status;
    private final String message;
    private Map<String, Object> data;

    private static final String DEFAULT_SUCCESS_MESSAGE = "success";
    private static final String DEFAULT_FAIL_MESSAGE = "fail";

    @Builder
    private ResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseDto init(){
        this.data = new HashMap<>();
        return this;
    }

    public ResponseDto add(final String key, final Object value) {
        this.data.put(key, value);
        return this;
    }

    public static ResponseDto success() {
        return ResponseDto.builder()
                .status(HttpStatus.OK.value())
                .message(DEFAULT_SUCCESS_MESSAGE)
                .build();
    }

    public static ResponseDto success(final String message) {
        return ResponseDto.builder()
                .status(HttpStatus.OK.value())
                .message(message)
                .build();
    }


    public static ResponseDto fail() {
        return ResponseDto.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(DEFAULT_FAIL_MESSAGE)
                .build();
    }

    public static ResponseDto fail(final String message) {
        return ResponseDto.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(message)
                .build();
    }
}
