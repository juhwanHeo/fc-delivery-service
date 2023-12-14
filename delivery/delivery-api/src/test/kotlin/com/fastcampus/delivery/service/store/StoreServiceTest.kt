package com.fastcampus.delivery.service.store

import org.springframework.boot.test.context.SpringBootTest

import com.fastcampus.delivery.domain.store.StoreStatus
import com.fastcampus.delivery.repository.store.Store
import com.fastcampus.delivery.repository.store.StoreRepository
import java.math.BigDecimal

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

@SpringBootTest
class StoreServiceTest(
    private val storeService: StoreService,
    private val storeRepository: StoreRepository,
) : BehaviorSpec({
    given("상점 생성시") {
        When("정상적인 input") {
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

            val saved = storeRepository.save(store)

            then("상점이 정상적으로 생성됨을 확인한다.") {
                saved shouldNotBe -1L
                val store = storeService.findByStoreId(saved.storeId)
                store shouldNotBe null

                saved?.email shouldBe store.email
                saved?.createdBy shouldBe store.createdBy
            }
        }
    }
})