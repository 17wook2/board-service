package com.wook2.projectboard.dto;

import com.wook2.projectboard.entity.Article;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.wook2.projectboard.entity.Article} entity
 */
@Data
@Builder
public class ArticleResponseDto implements Serializable {
    private final LocalDateTime createdDate;
    private final LocalDateTime lastModifiedDate;
    private final String createdBy;
    private final String lastModifedBy;
    private final String title;
    private final String content;
    private final String hashtag;

    public static ArticleResponseDto toDto(Article article) {
        return ArticleResponseDto.builder()
                .content(article.getContent())
                .title(article.getTitle())
                .hashtag(article.getHashtag())
                .createdBy(article.getCreatedBy())
                .createdDate(article.getCreatedDate())
                .lastModifedBy(article.getLastModifedBy())
                .lastModifiedDate(article.getLastModifiedDate())
                .build();
    }
}