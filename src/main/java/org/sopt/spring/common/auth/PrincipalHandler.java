package org.sopt.spring.common.auth;

import org.sopt.spring.common.exception.ErrorMessage;
import org.sopt.spring.common.exception.UnauthorizedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class PrincipalHandler {

    private static final String ANONYMOUS_USER = "anonymousUser";

    public Long getUserIdFromPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        isPrincipalNull(principal);
        return Long.valueOf(principal.toString());
    }

    public void isPrincipalNull(
            final Object principal
    ) {
        if (principal.toString().equals(ANONYMOUS_USER)) {
            throw new UnauthorizedException(ErrorMessage.JWT_UNAUTHORIZED_EXCEPTION);
        }
    }
}