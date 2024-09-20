package jjfactory.redis_study.template

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisStringService(
    private val redisTemplate: RedisTemplate<String, Any>
) {

    // 키에 값 설정 (SET)
    fun setValue(key: String, value: String) {
        redisTemplate.opsForValue().set(key, value)
    }

    // 키의 값 조회 (GET)
    fun getValue(key: String): Any? {
        return redisTemplate.opsForValue().get(key)
    }

    // 키 삭제 (DEL)
    fun deleteKey(key: String): Boolean {
        return redisTemplate.delete(key)
    }

    // 값 증가 (INCR)
    fun incrementValue(key: String, delta: Long = 1): Long? {
        return redisTemplate.opsForValue().increment(key, delta)
    }

    // 값 감소 (DECR)
    fun decrementValue(key: String, delta: Long = 1): Long? {
        return redisTemplate.opsForValue().increment(key, -delta)
    }

    // 값에 문자열 추가 (APPEND)
    fun appendToValue(key: String, value: String): Int? {
        return redisTemplate.opsForValue().append(key, value)
    }
}