package com.fastcampus.delivery.controller.cart

import com.fastcampus.delivery.controller.cart.dto.CartMenuDto
import com.fastcampus.delivery.controller.cart.dto.CartQueryRequest
import com.fastcampus.delivery.controller.cart.dto.CartQueryResponse
import com.fastcampus.delivery.service.cart.CartService
import io.github.oshai.kotlinlogging.KotlinLogging
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "CartController", description = "장바구니 컨트롤러")
@RestController
@RequestMapping("/apis/carts")
class CartController(
    private val cartService: CartService,
) {
    companion object {
        private val logger = KotlinLogging.logger(this::class.java.name)
    }

    @GetMapping("/items")
    @Operation(
        summary = "장바구니 목록 요청",
        description = "현재 장바구니에 담긴 음식 메뉴 목록을 조회한다.",
        responses = [ApiResponse(
            responseCode = "200",
            description = "장바구니 요청에 대한 응답",
            useReturnTypeSchema = true,
        )]
    )
    fun list(
        @Parameter(
            schema = Schema(implementation = CartQueryRequest::class),
            name = "cartQueryRequest", description = "장바구니 조회 요청", required = true, `in` = ParameterIn.QUERY)
        cartQueryRequest: CartQueryRequest,
        pageable: Pageable,
    ): ResponseEntity<CartQueryResponse> {
        logger.info { ">>> 장바구니 조회 요청 $cartQueryRequest" }
        val cartMenuPage = cartService.findByCustomerId(cartQueryRequest.customerId)
        val cartItems = cartMenuPage.map { CartMenuDto.from(it) }
        return ResponseEntity.ok(
            CartQueryResponse(
                customerId = cartQueryRequest.customerId,
                cartItems = cartItems,
            )
        )
    }
}