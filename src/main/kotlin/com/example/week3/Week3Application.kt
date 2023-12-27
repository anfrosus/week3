package com.example.week3

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class Week3Application

fun main(args: Array<String>) {
    runApplication<Week3Application>(*args)
}
