package app

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EasyTripServiceApplication

fun main(args: Array<String>) {
  val mapper = jacksonObjectMapper()
  mapper.registerKotlinModule()
  runApplication<EasyTripServiceApplication>(*args)
}
