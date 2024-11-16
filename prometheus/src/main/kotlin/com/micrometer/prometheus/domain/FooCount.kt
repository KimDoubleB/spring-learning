package com.micrometer.prometheus.domain

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.stereotype.Component

@Component
class FooCount(meterRegistry: MeterRegistry) {

    private val counter = Counter.builder("domain.foo.count")
        .description("foo domain count")
        .tag("how", "counter")
        .register(meterRegistry)

    fun increase() {
        counter.increment()
    }

}
