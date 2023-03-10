package com.wook2.projectboard.repository;

import com.wook2.projectboard.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
