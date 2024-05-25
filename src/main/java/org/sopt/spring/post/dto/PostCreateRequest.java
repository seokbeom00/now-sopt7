package org.sopt.spring.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostCreateRequest(
    String title,
    @NotBlank(message = "내용을 반드시 입력해야 합니다.")
    @Size(max = 10, message = "내용은 300자를 초과할 수 없습니다.")
    String content
) {
}
