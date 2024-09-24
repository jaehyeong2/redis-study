package jjfactory.redis_study.template

import jjfactory.redis_study.infrastructure.ProductRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class RedisCartService(
    private val redisTemplate: RedisTemplate<String, Any>,
    private val productRepository: ProductRepository
) {

    fun addProductToCart(sessionUserId: Long, productId: Long){
        val product = productRepository.findByIdOrNull(productId) ?: throw NotFoundException()

        val cartKey = "cart:$sessionUserId"
        redisTemplate.opsForSet().add(cartKey, productId)
        redisTemplate.expire(cartKey, 1, TimeUnit.HOURS)
    }

    fun getMyCarts(sessionUserId: Long): Set<Any> {
        val cartKey = "cart:$sessionUserId"
        return redisTemplate.opsForSet().members(cartKey) ?: emptySet()
    }

    fun clearCart(sessionUserId: Long) {
        val cartKey = "cart:$sessionUserId"

        redisTemplate.delete(cartKey)
    }
}