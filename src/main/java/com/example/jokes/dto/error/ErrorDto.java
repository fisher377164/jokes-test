package com.example.jokes.dto.error;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorDto(String message,
                       LocalDateTime timestamp) implements Serializable {
    public ErrorDto(final String message) {
        this(message, LocalDateTime.now());
    }
}
