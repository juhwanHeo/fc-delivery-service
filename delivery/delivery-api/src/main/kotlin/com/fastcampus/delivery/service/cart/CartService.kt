package com.fastcampus.delivery.service.cart

import com.fastcampus.delivery.domain.cart.CartMenu
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

    fun findByCustomerId(customerId: Long): List<CartMenu> {
        val cartOptional = cartRepository.findAllByCustomerIdAndIsDeleted(customerId, false)
        if (cartOptional.isEmpty) {
            return Collections.emptyList()
        }
        val cart = cartOptional.get()

        logger.info { ">>> cart $cart" }
        return cartItemRepository.findAllByCartId(cart.cartId)
    }

}