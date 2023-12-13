package com.fastcampus.delivery.service.store

import com.fastcampus.delivery.exception.NotFoundStoreException
import com.fastcampus.delivery.repository.store.Store
import com.fastcampus.delivery.repository.store.StoreRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.Optional

@Service
@Transactional
class StoreService(
    private val storeRepository: StoreRepository,
) {

    fun findByStoreId(storeId: Long): Store {
        return storeRepository.findByIdOrNull(storeId)
            ?: throw NotFoundStoreException("상점 정보를 찾을 수 없습니다. $storeId")
    }
}