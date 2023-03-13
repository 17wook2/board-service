package com.wook2.projectboard.api;

import com.wook2.projectboard.dto.ArticleRequestDto;
import com.wook2.projectboard.dto.ArticleResponseDto;
import com.wook2.projectboard.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/articles")
    public Page<ArticleResponseDto> findAll(Pageable pageable) {
        return articleService.findAll(pageable);
    }

    @GetMapping("/articles/{id}")
    public ArticleResponseDto findArticleById(@PathVariable Long id) {
        return articleService.findArticleById(id);
    }

    @PostMapping("/articles")
    public Long saveArticle(@RequestBody ArticleRequestDto articleRequestDto){
        return articleService.saveArticle(articleRequestDto);
    }

    @PutMapping("/articles/{id}")
    public Long updateArticle(@PathVariable Long id, @RequestBody ArticleRequestDto articleRequestDto) {
        return articleService.updateArticle(id, articleRequestDto);
    }

    @DeleteMapping("/articles/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }
}
