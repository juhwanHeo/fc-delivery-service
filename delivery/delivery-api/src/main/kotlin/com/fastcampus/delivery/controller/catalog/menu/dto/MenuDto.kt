package com.fastcampus.delivery.controller.catalog.menu.dto

import com.fastcampus.delivery.repository.menu.Menu
import java.math.BigDecimal

data class MenuDto(
    val menuId: Long,
    val menuName: String,
    val storeId: Long,
    val price: BigDecimal,
    val description: String,
    val menuImageUrl: String,
) {
    companion object {
        fun from(menu: Menu): MenuDto {
            return MenuDto(
                menuId = menu.menuId,
                menuName = menu.menuName,
                storeId = menu.storeId,
                price = menu.price,
                description = menu.description,
                menuImageUrl = menu.menuMainImageUrl,
            )
        }
    }
}