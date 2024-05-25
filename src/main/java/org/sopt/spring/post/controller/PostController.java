package org.sopt.spring.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.spring.post.domain.Post;
import org.sopt.spring.post.dto.PostCreateRequest;
import org.sopt.spring.post.dto.PostViewResponse;
import org.sopt.spring.post.service.PostService;
import org.sopt.spring.common.success.SuccessMessage;
import org.sopt.spring.common.success.SuccessStatusResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping("/post")
    public ResponseEntity<SuccessStatusResponse> postBlog(
            @RequestHeader Long memberId,
            @RequestHeader Long blogId,
            @Valid @RequestBody PostCreateRequest postCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(
                        "Location", postService.post(memberId, blogId, postCreateRequest))
                .body(SuccessStatusResponse.of(SuccessMessage.POSTING_CREATE_SUCCESS));
    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<SuccessStatusResponse<PostViewResponse>> getBlog(
            @PathVariable Long postId) {
        return ResponseEntity.ok(SuccessStatusResponse.of(SuccessMessage.POSTING_VIEW_SUCCESS, postService.getPost(postId)));
    }

}
