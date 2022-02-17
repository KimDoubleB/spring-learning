package com.junit5.tag;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("unit")
class ApplicationUnitTests {

    @Test
    @Tag("foo")
    void fooTest() {
        var foo = true;
        assertThat(foo).isTrue();
    }

    @Test
    @Tag("bar")
    @Tag("feature-001")  // Multiple tag
    void barTest() {
        var bar = false;
        assertThat(bar).isFalse();
    }

    @Test
    @Tags({
            @Tag("baz"),
            @Tag("feature-001")
    })
    void bazTest() {
        var baz = List.of("baz");
        assertThat(baz).contains("baz");
    }

}
