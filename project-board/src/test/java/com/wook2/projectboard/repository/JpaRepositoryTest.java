package com.wook2.projectboard.repository;
import com.wook2.projectboard.config.JpaConfig;
import com.wook2.projectboard.entity.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@ActiveProfiles("testdb")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    @Autowired private ArticleRepository articleRepository;
    @Autowired private ArticleCommentRepository articleCommentRepository;

    @DisplayName("select 테스트")
    @Test
    void dataJPA_test(){
        long count = articleRepository.count();
        Article article = Article.of("title1", "content1", "hashtag1");
        Article savedArticle = articleRepository.save(article);
        long updatedCount = articleRepository.count();
        assertThat(updatedCount).isEqualTo(count + 1);
    }

    @DisplayName("update 테스트")
    @Test
    void dataJPA_update_test(){
        Article article = articleRepository.findById(1L).orElseThrow();
        article.setHashtag("updatedHashtag");
        Article savedArticle = articleRepository.saveAndFlush(article);
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", "updatedHashtag");

    }


}