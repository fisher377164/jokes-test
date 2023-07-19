package com.example.jokes.config;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Builder
@ConfigurationProperties(prefix = "jokes", ignoreUnknownFields = false)
public class JokesProperties {
    private final AsyncProperties async;
    private final AppProperties app;
    private final ExternalProperties external;

    public record AsyncProperties(int corePoolSize, int maxPoolSize, String threadNamePrefix) {
    }

    public record AppProperties(int defaultPageSize) {
    }

    public record ExternalProperties(String url) {
    }

}
