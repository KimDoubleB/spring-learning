package com.micrometer.prometheus

import com.micrometer.prometheus.domain.FooData
import io.micrometer.core.aop.CountedAspect
import io.micrometer.core.aop.TimedAspect
import io.micrometer.core.instrument.FunctionCounter
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.binder.MeterBinder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MetricConfig {

    @Bean
    fun countedAspect(registry: MeterRegistry) = CountedAspect(registry)

    @Bean
    fun timedAspect(registry: MeterRegistry) = TimedAspect(registry)

    @Bean
    fun fooDataMeterBinder(fooData: FooData): MeterBinder {
        return MeterBinder { registry: MeterRegistry ->
            FunctionCounter.builder("domain.foo.count", fooData) { fooData.get().toDouble() }
                .description("foo domain count")
                .tags("how", "meterBinder")
                .register(registry)
        }
    }

}
