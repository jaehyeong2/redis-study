package jjfactory.redis_study.infrastructure

import jjfactory.redis_study.domain.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long> {
}