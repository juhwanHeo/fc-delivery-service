package com.fastcampus.delivery.repository.cartitem

import com.fastcampus.delivery.domain.cart.CartMenu
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CartItemRepository : JpaRepository<CartItem, Long> {

    @Query(
        value = """
            SELECT new com.fastcampus.delivery.domain.cart.CartMenu(
            ci.cartId,
            ci.cartItemId,
            ci.menuId,
            m.menuName,
            m.menuMainImageUrl,
            m.price,
            ci.quantity)
            FROM CartItem ci
            JOIN Menu m ON m.menuId = ci.menuId
            WHERE ci.cartId = :cartId
        """
    )
    fun findAllByCartId(@Param("cartId") cartId: Long): List<CartMenu>
}