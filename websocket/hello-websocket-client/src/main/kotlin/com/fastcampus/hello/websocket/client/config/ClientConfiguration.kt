package com.fastcampus.hello.websocket.client.config

import com.fastcampus.hello.websocket.client.handler.ClientWebSocketStompSessionHandler
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.simp.stomp.StompHeaders
import org.springframework.messaging.simp.stomp.StompSessionHandler
import org.springframework.web.socket.client.WebSocketClient
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import org.springframework.web.socket.messaging.WebSocketStompClient


@Configuration
class ClientConfiguration {

    @Value("\${chatting.endpoint}")
    lateinit var endpoint: String

    @Bean
    fun webSocketStompClient(
        webSocketStompClient: WebSocketStompClient,
        stompSessionHandler: StompSessionHandler,
    ) : WebSocketStompClient {
        webSocketStompClient.messageConverter = MappingJackson2MessageConverter()

        val stompHeaders = StompHeaders()
        stompHeaders.add("name", "sonic")

        val urlVariables = arrayOf<Any>()
        webSocketStompClient.connectAsync(endpoint, null, stompHeaders, stompSessionHandler, urlVariables)

        return webSocketStompClient
    }

    @Bean
    fun webSocketClient(): WebSocketStompClient? {
        val webSocketClient: WebSocketClient = StandardWebSocketClient()
        return WebSocketStompClient(webSocketClient)
    }

    @Bean
    fun stompSessionHandler(
        objectMapper: ObjectMapper,
    ): StompSessionHandler? {
        return ClientWebSocketStompSessionHandler(objectMapper)
    }
}