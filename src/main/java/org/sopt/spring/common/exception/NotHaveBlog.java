package org.sopt.spring.common.exception;

public class NotHaveBlog extends BusinessException{
    public NotHaveBlog(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
