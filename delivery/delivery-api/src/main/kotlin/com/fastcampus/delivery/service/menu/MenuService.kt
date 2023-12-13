package com.fastcampus.delivery.service.menu

import com.fastcampus.delivery.exception.NotFoundMenuException
import com.fastcampus.delivery.repository.menu.Menu
import com.fastcampus.delivery.repository.menu.MenuRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional
class MenuService(
    private val menuRepository: MenuRepository
) {
    fun findByStoreId(storeId: Long): List<Menu> {
        return menuRepository.findAllByStoreId(storeId)
    }

    fun findByMenuId(menuId: Long): Menu {
        return menuRepository.findByIdOrNull(menuId) ?:
            throw NotFoundMenuException("메뉴 정보를 찾을 수 없습니다. $menuId")
    }
}