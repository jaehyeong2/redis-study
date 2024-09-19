package jjfactory.redis_study

import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {
    @Bean
    fun redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<String, Any> {
        val template = RedisTemplate<String, Any>()
        template.connectionFactory = connectionFactory

        val serializer = Jackson2JsonRedisSerializer(Any::class.java)

        template.keySerializer = StringRedisSerializer()  // key는 문자열로 직렬화
        template.valueSerializer = serializer  // value는 JSON 형식으로 직렬화
        template.hashKeySerializer = StringRedisSerializer()
        template.hashValueSerializer = serializer

        template.afterPropertiesSet()
        return template
    }
}