package com.micrometer.prometheus.domain

import org.springframework.stereotype.Component
import java.util.concurrent.atomic.AtomicLong

@Component
class FooData {

    val count = AtomicLong(0)


    fun get(): Long {
        return count.get()
    }

    fun increaseAndGet() {
        count.incrementAndGet()
    }

}
