package jjfactory.redis_study.infrastructure

import jjfactory.redis_study.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
}