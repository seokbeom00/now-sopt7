package org.sopt.spring.common.exception;

public class NotFoundException extends BusinessException{
    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
