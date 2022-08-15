package com.example.demo.domain.user;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserCustomRepositoryImplTest {

    @Test
    void name() {
        var of = List.of();
        var strings = of.stream()
                              .map(a -> a.toString())
                              .toList();
        System.out.println("strings = " + strings);
    }
}