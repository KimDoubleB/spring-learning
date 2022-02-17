package com.junit5.tag;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("integration")
class ApplicationIntegrationTests {

    @Test
    void integrationTest() {
        // SpringBoot start
        assertThat("Start").isEqualTo("Start");
    }

}
