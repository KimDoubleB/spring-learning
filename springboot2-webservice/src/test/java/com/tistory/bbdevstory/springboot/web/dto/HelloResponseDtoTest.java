package com.tistory.bbdevstory.springboot.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void lombok_function_test(){
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);

        // Test 가 통과된다면, lombok - Getter, RequiredArgsConstructor 가 잘 작동한다는 것이 증명되는 것
    }
}
