package com.fastcampus.delivery.repository

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import java.time.OffsetDateTime

@MappedSuperclass
abstract class BaseEntity(
    createdBy: String,
) {
    @Column(name = "is_deleted", nullable = false)
    var isDeleted: Boolean = false

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    var createdAt: OffsetDateTime = OffsetDateTime.now()

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = true)
    var updatedAt: OffsetDateTime = OffsetDateTime.now()

    @Column(name = "created_by", nullable = false)
    var createdBy: String? = createdBy

    @Column(name = "updated_by", nullable = true)
    var updatedBy: String? = null
}