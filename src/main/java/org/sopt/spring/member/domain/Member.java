package org.sopt.spring.member.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.spring.blog.domain.Blog;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Part part;
    private int age;

    @OneToOne
    private Blog blog;

    @Builder
    private Member(String name, Part part, int age) {
        this.name = name;
        this.part = part;
        this.age = age;
    }

    public static Member create(String name, Part part, int age) {
        return Member.builder()
                .name(name)
                .age(age)
                .part(part)
                .build();
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}
