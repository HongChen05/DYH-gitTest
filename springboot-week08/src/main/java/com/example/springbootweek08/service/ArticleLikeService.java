package com.example.springbootweek08.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Set;

@Service
public class ArticleLikeService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final String LIKE_COUNT_KEY = "article:like:count:";
    private static final String LIKE_USERS_KEY = "article:like:users:";
    private static final String HOT_RANK_KEY = "article:hot:rank";

    /**
     * 给文章点赞
     */
    public void likeArticle(Long articleId, Long userId) {
        String userKey = LIKE_USERS_KEY + articleId;
        String countKey = LIKE_COUNT_KEY + articleId;

        // 1. 判断用户是否已点赞
        if (Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(userKey, userId))) {
            throw new RuntimeException("您已经点过赞了，无需重复点赞");
        }

        // 2. 将用户加入点赞集合
        redisTemplate.opsForSet().add(userKey, userId);

        // 3. 点赞数 +1
        redisTemplate.opsForValue().increment(countKey, 1);

        // 4. 更新排行榜（点赞数为 score）
        redisTemplate.opsForZSet().incrementScore(HOT_RANK_KEY, articleId, 1);
    }

    /**
     * 获取文章点赞数
     */
    public Long getLikeCount(Long articleId) {
        String countKey = LIKE_COUNT_KEY + articleId;
        Object count = redisTemplate.opsForValue().get(countKey);
        return count == null ? 0 : Long.parseLong(count.toString());
    }

    /**
     * 获取热度 TopN 文章（从高到低）
     */
    public Set<Object> getHotRank(int topN) {
        return redisTemplate.opsForZSet().reverseRange(HOT_RANK_KEY, 0, topN - 1);
    }
}