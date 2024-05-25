package org.sopt.spring.common.success;

import com.fasterxml.jackson.annotation.JsonInclude;

public record SuccessStatusResponse<T>(
        int status,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        T data

) {
    public static <T> SuccessStatusResponse<T> of(SuccessMessage successMessage, T bodyData) {
        return new SuccessStatusResponse<>(successMessage.getStatus(), successMessage.getMessage(), bodyData);
    }

    public static <T> SuccessStatusResponse<T> of(SuccessMessage successMessage) {
        return new SuccessStatusResponse<>(successMessage.getStatus(), successMessage.getMessage(), null);
    }
}
