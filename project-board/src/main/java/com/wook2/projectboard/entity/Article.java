package com.wook2.projectboard.entity;

import com.wook2.projectboard.dto.ArticleRequestDto;
import jakarta.persistence.*;
import lombok.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private String title;
    @Column(nullable = false, length = 10000) private String content;

    @Setter private String hashtag;

    @ToString.Exclude
    @OrderBy("createdDate DESC")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();


    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(String title, String content, String hashtag){
        return new Article(title,content,hashtag);
    }

    private void addComment(ArticleComment articleComment){
        articleComments.add(articleComment);
        articleComment.setArticle(this);
    }

    private void removeComment(ArticleComment articleComment) {
        articleComments.remove(articleComment);
        articleComment.setArticle(null);
    }

    public void update(ArticleRequestDto articleRequestDto){
        title = articleRequestDto.getTitle();
        content = articleRequestDto.getContent();
        hashtag = articleRequestDto.getHashtag();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
