package com.example.springbootweek08.controller;

import com.example.springbootweek08.service.ArticleLikeService;
import com.example.springbootweek08.common.dto.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Set;

@RestController
@RequestMapping("/api/article")
public class ArticleLikeController {

    @Resource
    private ArticleLikeService articleLikeService;

    /**
     * 点赞接口
     */
    @PostMapping("/like")
    public ApiResult<String> likeArticle(
            @RequestParam Long articleId,
            @RequestParam Long userId) {
        articleLikeService.likeArticle(articleId, userId);
        return ApiResult.success("点赞成功");
    }

    /**
     * 获取文章点赞数
     */
    @GetMapping("/like/count")
    public ApiResult<Long> getLikeCount(@RequestParam Long articleId) {
        return ApiResult.success(articleLikeService.getLikeCount(articleId));
    }

    /**
     * 获取热度排行榜 Top3
     */
    @GetMapping("/hot/rank")
    public ApiResult<Set<Object>> getHotRank() {
        return ApiResult.success(articleLikeService.getHotRank(3));
    }
}