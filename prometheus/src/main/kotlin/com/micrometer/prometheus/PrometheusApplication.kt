package com.micrometer.prometheus

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PrometheusApplication

fun main(args: Array<String>) {
    runApplication<PrometheusApplication>(*args)
}
