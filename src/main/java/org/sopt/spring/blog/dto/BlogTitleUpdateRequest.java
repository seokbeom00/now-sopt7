package org.sopt.spring.blog.dto;

import jakarta.validation.constraints.Size;

public record BlogTitleUpdateRequest(
        @Size(max = 10, message = "블로그 제목이 최대 글자수(10자)를 초과하였습니다")
        String title
) {
}
