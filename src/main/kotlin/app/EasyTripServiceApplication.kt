package app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EasyTripServiceApplication

fun main(args: Array<String>) {
	runApplication<EasyTripServiceApplication>(*args)
}
