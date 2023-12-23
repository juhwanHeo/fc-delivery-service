package com.fastcampus.delivery.service.cart

import com.fastcampus.delivery.domain.cart.CartMenu
import com.fastcampus.delivery.exception.NotFoundException
import com.fastcampus.delivery.repository.cart.Cart
import com.fastcampus.delivery.repository.cart.CartRepository
import com.fastcampus.delivery.repository.cartitem.CartItemRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class CartService(
    private val cartRepository: CartRepository,
    private val cartItemRepository: CartItemRepository,
) {

    companion object {
        private val logger = KotlinLogging.logger(this::class.java.name)
    }

    fun findByCustomerId(
        customerId: Long,
    ): Cart {
        return cartRepository.findAllByCustomerIdAndIsDeleted(customerId, false)
            .orElseThrow {
                throw NotFoundException("고객님의 장바구니 정보를 찾을 수 없습니다.")
            }
    }
}