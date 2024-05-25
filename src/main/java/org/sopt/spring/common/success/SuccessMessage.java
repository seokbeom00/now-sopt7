package org.sopt.spring.common.success;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SuccessMessage {

    BLOG_CREATE_SUCCESS(HttpStatus.CREATED.value(), "블로그 생성이 완료되었습니다"),
    POSTING_CREATE_SUCCESS(HttpStatus.CREATED.value(), "게시글 포스팅이 완료되었습니다"),
    POSTING_VIEW_SUCCESS(HttpStatus.OK.value(), "게시글 조회가 완료되었습니다.")
    ;
    private final int status;
    private final String message;
}
