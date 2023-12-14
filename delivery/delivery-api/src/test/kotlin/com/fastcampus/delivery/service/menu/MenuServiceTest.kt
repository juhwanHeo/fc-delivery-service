package com.fastcampus.delivery.service.menu

import org.springframework.boot.test.context.SpringBootTest

import com.fastcampus.delivery.domain.catalog.menu.MenuStatus
import com.fastcampus.delivery.domain.store.StoreStatus
import com.fastcampus.delivery.repository.menu.Menu
import com.fastcampus.delivery.repository.menu.MenuRepository
import com.fastcampus.delivery.repository.store.Store
import com.fastcampus.delivery.repository.store.StoreRepository
import java.math.BigDecimal

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

@SpringBootTest
class MenuServiceTest(
    private val menuService: MenuService,
    private val menuRepository: MenuRepository,
    private val storeRepository: StoreRepository,
) : BehaviorSpec({
    given("메뉴 생성시") {
        val store = Store(
            email = "test@test.com",
            businessNumber = "08012345678",
            storeName = "test stroe name 1",
            storeMainImageUrl = "/test/path/test.png",
            storePhone = "test",
            address = "test",
            password = "1234",
            bankAccount = "teet bank account",
            bankName = "test bank Name",
            description = "test description",
            storeStatus = StoreStatus.READY,
            deliveryFee = BigDecimal("3000"),
            deliveryTime = "2023.01.01",
            reviewGrade = 4,
            minimumOrderPrice = BigDecimal("15000"),
            createdBy = "juhwan"
        )

        val storeSaved = storeRepository.save(store)
        When("정상적인 input") {
            val menu = Menu(
                storeId = storeSaved.storeId,
                menuName = "test Menu",
                menuMainImageUrl = "/menu/image/test.png",
                price = BigDecimal("4000"),
                menuStatus = MenuStatus.READY,
                description = "test description",
                createdBy = "juhwan"
            )

            val saved = menuRepository.save(menu)
            println(saved)
            then("메뉴가 정상적으로 생성됨을 확인한다.") {
                saved shouldNotBe -1L
                val menus = menuService.findByStoreId(saved.storeId)
                menus shouldNotBe null
                menus.size shouldBe 1
                saved?.storeId shouldBe  menus[0].storeId
                saved?.createdBy shouldBe menus[0].createdBy
            }
        }
    }
})
