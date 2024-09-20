package jjfactory.redis_study.service

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate

@SpringBootTest
class RankingServiceTest {
    @Autowired lateinit var redisTemplate: RedisTemplate<String, Any>
    @Autowired lateinit var rankingService: RankingService

    @BeforeEach
    fun clearRedis() {
        redisTemplate.delete("contentRanking")
    }

    @Test
    fun `조회수 증가 메소드 호출 시 레디스 스코어 증가`() {
        //given
        val contentId = "content1"

        //when
        rankingService.incrementViewCount(contentId)

        //then
        assertThat(redisTemplate.opsForZSet().score("contentRanking", contentId)).isEqualTo(1.0)
    }

    @Test
    fun `상위랭킹 조회 시 리미트만큼 조회된다`() {
        val limit = 3
        val expectedSet: Set<Any> = setOf("content1", "content2", "content3")

        rankingService.incrementViewCount("content1")
        rankingService.incrementViewCount("content1")
        rankingService.incrementViewCount("content2")
        rankingService.incrementViewCount("content3")

        assertThat(rankingService.getTopRankings(limit)).isEqualTo(expectedSet)
    }
}