package com.fastcampus.delivery.service.cartitem

import com.fastcampus.delivery.domain.cart.CartMenu
import com.fastcampus.delivery.repository.cartitem.CartItemRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class CartItemService(
    private val cartItemRepository: CartItemRepository,
) {
    fun findAllByCartId(
        cartId: Long,
    ): List<CartMenu> {
        return cartItemRepository.findAllByCartId(cartId)
    }
}
