package com.micrometer.prometheus

import com.micrometer.prometheus.domain.FooCount
import com.micrometer.prometheus.domain.FooData
import io.micrometer.core.annotation.Counted
import io.micrometer.core.annotation.Timed
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/metrics/count")
class CounterController(
    private val fooCount: FooCount,
    private val fooData: FooData,
) {

    @GetMapping("/counter")
    fun countedUsingCounter(): ResponseEntity<String> {
        fooCount.increase()
        return ResponseEntity.ok(DONE)
    }

    @GetMapping("/meterBinder")
    fun countedUsingMeterBinder(): ResponseEntity<String> {
        fooData.increaseAndGet()
        return ResponseEntity.ok(DONE)
    }

    @Counted(
        value = "domain.foo.count",
        description = "foo domain count",
        extraTags = ["how", "annotation"],
    )
    @GetMapping("/annotation")
    fun countedUsingAnnotation(): ResponseEntity<String> {
        return ResponseEntity.ok(DONE)
    }

    companion object {
        const val DONE = "COUNT DONE"
    }
}


@RestController
@RequestMapping("/metrics/timer")
class TimerController {

    @Timed(
        value = "domain.foo.timer",
        description = "foo domain timer",
        extraTags = ["how", "annotation"],
    )
    @GetMapping("/annotation")
    fun countedUsingAnnotation(): ResponseEntity<String> {
        return ResponseEntity.ok(DONE)
    }

    companion object {
        const val DONE = "TIMER DONE"
    }

}
