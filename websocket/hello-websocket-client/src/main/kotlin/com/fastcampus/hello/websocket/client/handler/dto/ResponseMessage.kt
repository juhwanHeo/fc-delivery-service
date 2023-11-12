package com.fastcampus.hello.websocket.client.handler.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseMessage(
    @JsonProperty("content") val content : String,
)
