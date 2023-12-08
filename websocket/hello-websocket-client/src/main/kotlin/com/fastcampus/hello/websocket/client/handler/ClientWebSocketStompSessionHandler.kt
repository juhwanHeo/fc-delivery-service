package com.fastcampus.hello.websocket.client.handler

import com.fastcampus.hello.websocket.client.handler.dto.ResponseMessage
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.core.annotation.Order
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaders
import org.springframework.messaging.simp.stomp.StompSession
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter
import org.springframework.stereotype.Component
import java.lang.reflect.Type

@Component
@Order(value = 1)
class ClientWebSocketStompSessionHandler(
    val objectMapper: ObjectMapper,
) : StompSessionHandlerAdapter() {
    companion object {
        private const val SUBSCRIPTION_TOPIC = "/topic/chatting"
        private const val PUBLISH_DEST = "/app/chatting-message"

        private val logger = KotlinLogging.logger(this.javaClass.name)
    }
    override fun handleFrame(
        headers: StompHeaders,
        payload: Any?,
    ) {
        logger.info { ">>> handleFrame, $headers" }

        // 구독한 채널의 메세지 받기
        val responseRawMessage = String(payload as ByteArray)
        val responseMessage = objectMapper.readValue(responseRawMessage, ResponseMessage::class.java)
        logger.info { "content = ${responseMessage.content}" }
    }

    override fun getPayloadType(
        headers: StompHeaders
    ) : Type {
        return Any::class.java
    }

    override fun afterConnected(
        session: StompSession,
        connectedHeaders: StompHeaders,
    ) {
        logger.info { ">>> afterConnected" }

        // 구독
        session.subscribe(SUBSCRIPTION_TOPIC, this)
        val params: MutableMap<String, Any> = HashMap()
        params["message"] = "반갑습니다. 저는 2번째 클라이언트입니다."

        // 메세지 보냄
        session.send(PUBLISH_DEST, params)
        logger.info { "params = $params" }
    }

    override fun handleException(
        session: StompSession,
        command: StompCommand?,
        headers: StompHeaders,
        payload: ByteArray,
        exception: Throwable,
    ) {
        logger.info { ">>> handleException" }
        logger.info { "exception: $exception" }
    }

    override fun handleTransportError(
        session: StompSession,
        exception: Throwable,
    ) {
        logger.info { ">>> handleTransportError, ${exception.message}" }
    }
}