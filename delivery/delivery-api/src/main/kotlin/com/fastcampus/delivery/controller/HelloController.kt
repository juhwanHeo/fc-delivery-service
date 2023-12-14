package com.fastcampus.delivery.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HelloController {

    companion object {
        private val logger = KotlinLogging.logger(this::class.java.name)
    }

    @GetMapping
    fun hello(

    ): String {
        logger.info { ">>> Hello !!" }

        return "Hello Delivery Service !!!"
    }
}