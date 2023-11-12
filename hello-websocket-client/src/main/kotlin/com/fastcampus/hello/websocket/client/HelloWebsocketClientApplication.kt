package com.fastcampus.hello.websocket.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloWebsocketClientApplication

fun main(args: Array<String>) {
	runApplication<HelloWebsocketClientApplication>(*args)
}
