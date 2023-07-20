package com.example.jokes.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@RequiredArgsConstructor
@EnableAsync(proxyTargetClass = true)
public class AsyncConfig {

    @Bean
    public Executor taskExecutor(JokesProperties appProperties) {
        final var async = appProperties.getAsync();
        final var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(async.corePoolSize());
        executor.setMaxPoolSize(async.maxPoolSize());
        executor.setThreadNamePrefix(async.threadNamePrefix());
        executor.initialize();
        return executor;
    }
}


