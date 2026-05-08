package com.example.springbootweek08.entity;

import lombok.Data;

@Data
public class Article {
    private Long id;
    private String title;
    private String content;
    private Integer likeCount;
}