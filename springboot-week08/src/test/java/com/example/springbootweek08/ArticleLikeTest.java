package com.example.springbootweek08;

import com.example.springbootweek08.service.ArticleLikeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Set;

@SpringBootTest
@Slf4j
public class ArticleLikeTest {

    @Autowired
    private ArticleLikeService articleLikeService;

    @Test
    public void testLike() {
        // 模拟用户给文章点赞
        articleLikeService.likeArticle(1L, 1001L);
        articleLikeService.likeArticle(1L, 1002L);
        articleLikeService.likeArticle(2L, 1001L);
        articleLikeService.likeArticle(2L, 1003L);
        articleLikeService.likeArticle(2L, 1004L);

        // 查看文章1的点赞数
        Long count1 = articleLikeService.getLikeCount(1L);
        log.info("文章1点赞数：{}", count1);

        // 查看热度排行榜
        Set<Object> hotRank = articleLikeService.getHotRank(3);
        log.info("热度排行榜Top3：{}", hotRank);
    }
}