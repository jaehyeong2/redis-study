package jjfactory.redis_study.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "users")
@Entity
class User(
    var name: String,
    var phone: String
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?= null
}