package org.sopt.spring.post.dto;

import org.sopt.spring.post.domain.Post;

public record PostViewResponse(
        String title,
        String content
) {
    public static PostViewResponse of(Post post) {
        return new PostViewResponse(post.getTitle(), post.getContent());
    }
}
