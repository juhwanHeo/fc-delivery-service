package com.fastcampus.delivery.repository.checkout

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CheckoutRepository : JpaRepository<Checkout, Long> {
    fun findByCustomerIdAndIsDeletedIsFalse(customerId: Long): Optional<Checkout>
    fun findAllByCheckoutIdIsNotAndCustomerIdIs(checkoutId: Long, customerId: Long): List<Checkout>
}