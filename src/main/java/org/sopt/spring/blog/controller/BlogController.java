package org.sopt.spring.blog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.spring.common.auth.PrincipalHandler;
import org.sopt.spring.common.success.SuccessMessage;
import org.sopt.spring.common.success.SuccessStatusResponse;
import org.sopt.spring.blog.service.BlogService;
import org.sopt.spring.blog.dto.BlogCreateRequest;
import org.sopt.spring.blog.dto.BlogTitleUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;
    private final PrincipalHandler principalHandler;

    @PostMapping("/blog")
    public ResponseEntity createBlog(
            @ModelAttribute BlogCreateRequest blogCreateRequest
    ) {
        return ResponseEntity.created(URI.create(blogService.create(
                principalHandler.getUserIdFromPrincipal(), blogCreateRequest))).build();
    }
    @PatchMapping("/blog/{blogId}/title")
    public ResponseEntity updateBlogTitle(
            @PathVariable Long blogId,
            @Valid @RequestBody BlogTitleUpdateRequest blogTitleUpdateRequest
    ){
        blogService.updateTitle(blogId, blogTitleUpdateRequest);
        return ResponseEntity.noContent().build();
    }
}
