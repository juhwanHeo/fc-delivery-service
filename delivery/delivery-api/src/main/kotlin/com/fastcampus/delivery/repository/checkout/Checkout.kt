package com.fastcampus.delivery.repository.checkout

import com.fastcampus.delivery.repository.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "checkouts")
class Checkout(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkout_id", nullable = false)
    val checkoutId: Long = 0,

    @Column(name = "customer_id", nullable = false)
    val customerId: Long,

    @Column(name = "store_id", nullable = false)
    val storeId: Long,

    createdBy: String,
): BaseEntity(createdBy)
