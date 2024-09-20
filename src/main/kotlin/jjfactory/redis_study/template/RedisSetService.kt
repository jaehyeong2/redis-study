package jjfactory.redis_study.template

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisSetService(
    private val redisTemplate: RedisTemplate<String, Any>
) {
    private val RANKING_KEY = "contentRanking"

    // 조회수 증가
    fun incrementViewCount(contentId: String) {
        redisTemplate.opsForZSet().incrementScore(RANKING_KEY, contentId, 1.0)
    }

    // 랭킹 상위 N개 항목 조회
    fun getTopRankings(limit: Int): Set<Any>? {
        return redisTemplate.opsForZSet().reverseRange(RANKING_KEY, 0, (limit - 1).toLong())
    }

    // 특정 콘텐츠의 현재 조회수 조회
    fun getViewCount(contentId: String): Double? {
        return redisTemplate.opsForZSet().score(RANKING_KEY, contentId)
    }

}