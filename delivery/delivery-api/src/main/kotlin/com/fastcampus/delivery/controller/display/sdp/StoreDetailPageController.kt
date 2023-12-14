package com.fastcampus.delivery.controller.display.sdp

import com.fastcampus.delivery.service.menu.MenuService
import com.fastcampus.delivery.service.store.StoreService
import com.fastcampus.delivery.controller.catalog.menu.dto.MenuDto
import com.fastcampus.delivery.controller.display.sdp.dto.StoreDetailPageResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/apis/display")
class StoreDetailPageController(
    private val storeService: StoreService,
    private val menuService: MenuService,
) {

    @GetMapping("/stores/{storeId}")
    fun list(
        @PathVariable storeId: Long
    ): StoreDetailPageResponse {
        val store  = storeService.findByStoreId(storeId)
        val menus = menuService.findByStoreId(storeId)
        val menuDTOs = menus.map { MenuDto.from(it) }

        return StoreDetailPageResponse(
            storeId = storeId,
            storeName = store.storeName,
            phone = store.storePhone,
            address = store.address,
            storeMainImageUrl = store.storeMainImageUrl,
            description = store.description,
            deliveryFee = store.deliveryFee,
            deliveryTime = store.deliveryTime,
            reviewGrade = store.reviewGrade,
            minimumOrderPrice = store.minimumOrderPrice,
            menus = menuDTOs,
        )
    }
}