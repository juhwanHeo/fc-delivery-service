package com.fastcampus.delivery.controller.display.mdp

import com.fastcampus.delivery.controller.display.mdp.dto.MenuDetailPageResponse
import com.fastcampus.delivery.domain.catalog.menu.MenuStatus
import com.fastcampus.delivery.service.menu.MenuService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/apis/display")
class MenuDetailPageController(
    private val menuService: MenuService,
) {
    private val logger = KotlinLogging.logger {}

    @GetMapping("/menus/{menuId}")
    fun detail(
        @PathVariable menuId: Long,
        @RequestParam storeId: Long,
    ) : MenuDetailPageResponse {
        val menu = menuService.findByMenuId(menuId)
        return MenuDetailPageResponse(
            menuId = menuId,
            storeId = storeId,
            menuName = menu.menuName,
            description = menu.description,
            menuMainImageUrl = menu.menuMainImageUrl,
            price = menu.price,
            menuStatue = MenuStatus.SALE,
        )
    }
}