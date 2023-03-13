package com.wook2.projectboard.dto;

import com.wook2.projectboard.entity.Article;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.wook2.projectboard.entity.Article} entity
 */
@Data
public class ArticleRequestDto{
    private final String title;
    private final String content;
    private final String hashtag;

    public Article toEntity(){
        return Article.of(title, content, hashtag);
    }
}