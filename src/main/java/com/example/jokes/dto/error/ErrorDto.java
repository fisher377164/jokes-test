package com.example.jokes.dto.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String message;

    private LocalDateTime timestamp;

    public ErrorDto(final String message) {
        this(message, LocalDateTime.now());
    }
}
