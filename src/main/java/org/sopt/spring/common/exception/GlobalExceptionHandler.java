package org.sopt.spring.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(ErrorMessage.MEMBER_NOT_FOUND));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.of(HttpStatus.BAD_REQUEST.value(),
                Objects.requireNonNull(e.getBindingResult().getFieldError().getDefaultMessage())));
    }

    @ExceptionHandler(NotHaveBlog.class)
    protected ResponseEntity<ErrorResponse> handleNotFoundException(NotHaveBlog e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(ErrorMessage.BLOG_CANT_USE));
    }
    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<ErrorResponse> handlerUnauthorizedException(UnauthorizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponse.of(ErrorMessage.JWT_UNAUTHORIZED_EXCEPTION.getStatus(), ErrorMessage.JWT_UNAUTHORIZED_EXCEPTION.getMessage()));
    }
}
