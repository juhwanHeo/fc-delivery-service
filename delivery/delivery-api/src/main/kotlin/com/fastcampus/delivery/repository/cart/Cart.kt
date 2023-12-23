package com.fastcampus.delivery.repository.cart

import com.fastcampus.delivery.repository.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "carts", catalog = "food_delivery")
class Cart(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    val cartId: Long = 0L,

    @Column(name = "customer_id")
    val customerId: Long,

    createdBy: String,
): BaseEntity(createdBy)