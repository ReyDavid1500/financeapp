package com.swissfintech.financeapp.common.base;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
}
