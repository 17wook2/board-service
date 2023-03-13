package com.wook2.projectboard.service;

import com.wook2.projectboard.dto.ArticleRequestDto;
import com.wook2.projectboard.dto.ArticleResponseDto;
import com.wook2.projectboard.entity.Article;
import com.wook2.projectboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    @Transactional(readOnly = true)
    public Page<ArticleResponseDto> findAll(Pageable pageable) {
        Page<Article> articlePage = articleRepository.findAll(pageable);
        return articlePage.map(ArticleResponseDto::toDto);
    }

    @Transactional(readOnly = true)
    public ArticleResponseDto findArticleById(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다." + id));
        return ArticleResponseDto.toDto(article);
    }
    public Long saveArticle(ArticleRequestDto articleRequestDto) {
        return articleRepository.save(articleRequestDto.toEntity()).getId();
    }

    public Long updateArticle(Long id, ArticleRequestDto articleRequestDto) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다." + id));
        article.update(articleRequestDto);
        return article.getId();
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}
