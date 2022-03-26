package com.getircase.readingisgood.application.domain.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String title;
    private int status;
    private String description;
    private String requestMethod;
    private String requestUri;
}
