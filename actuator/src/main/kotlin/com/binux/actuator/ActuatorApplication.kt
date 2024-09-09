package com.binux.actuator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ActuatorApplication

fun main(args: Array<String>) {
	runApplication<ActuatorApplication>(*args)
}
