package org.sopt.spring.post.service;

import lombok.RequiredArgsConstructor;
import org.sopt.spring.blog.domain.Blog;
import org.sopt.spring.blog.service.BlogService;
import org.sopt.spring.common.exception.ErrorMessage;
import org.sopt.spring.common.exception.NotFoundException;
import org.sopt.spring.common.exception.NotHaveBlog;
import org.sopt.spring.member.domain.Member;
import org.sopt.spring.member.service.MemberService;
import org.sopt.spring.post.domain.Post;
import org.sopt.spring.post.dto.PostCreateRequest;
import org.sopt.spring.post.dto.PostViewResponse;
import org.sopt.spring.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostService {
    private final BlogService blogService;
    private final PostRepository postRepository;
    private final MemberService memberService;
    @Transactional
    public String post(Long memberId, Long blogId, PostCreateRequest postCreateRequest) {
        Blog blog = blogService.findByBlogId(blogId);
        Member member = memberService.findById(memberId);
        if(!Objects.equals(member, blog.getMember())){
            throw new NotHaveBlog(ErrorMessage.BLOG_CANT_USE);
        }
        Post post = postRepository.save(Post.create(postCreateRequest, blog, memberId));
        blog.addPost(post);
        return post.getId().toString();
    }
    @Transactional(readOnly = true)
    public PostViewResponse getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.POSTING_NOT_FOUND));
        return PostViewResponse.of(post);
    }
}
