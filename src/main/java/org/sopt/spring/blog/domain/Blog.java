package org.sopt.spring.blog.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.spring.common.BaseTimeEntity;
import org.sopt.spring.blog.dto.BlogCreateRequest;
import org.sopt.spring.blog.dto.BlogTitleUpdateRequest;
import org.sopt.spring.member.domain.Member;
import org.sopt.spring.post.domain.Post;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor

public class Blog extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String title;

    private String description;
    private String imageUrl;

    @OneToMany(mappedBy = "blog")
    private List<Post> posts = new ArrayList<>();

    @OneToOne(mappedBy = "blog")
    private Member member;

    private Blog(Member member, String title, String description, String imageUrl) {
        this.member = member;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }
    public static Blog create(
            Member member,
            String title,
            String description,
            String imageUrl
    ) {
        return new Blog(member, title, description, imageUrl);
    }

    public void updateBlogTitle(BlogTitleUpdateRequest blogTitleUpdateRequest){
        this.title = blogTitleUpdateRequest.title();
    }

    public void addPost(Post post) {
        posts.add(post);
    }
}
