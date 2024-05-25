package org.sopt.spring.post.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.spring.blog.domain.Blog;
import org.sopt.spring.common.BaseTimeEntity;
import org.sopt.spring.post.dto.PostCreateRequest;

@Entity
@NoArgsConstructor
@Getter
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Blog blog;

    private Post(String title, String content, Blog blog, Long memberId) {
        this.title = title;
        this.content = content;
        this.blog = blog;
        this.memberId = memberId;
    }
    public static Post create(PostCreateRequest postCreateRequest, Blog blog, Long memberId) {
        return new Post(postCreateRequest.title(), postCreateRequest.content(), blog, memberId);
    }
}
