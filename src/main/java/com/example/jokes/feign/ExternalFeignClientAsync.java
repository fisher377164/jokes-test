package com.example.jokes.feign;

import com.example.jokes.dto.Joke;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ExternalFeignClientAsync {

    private final ExternalFeignClient externalFeignClient;

    @Async
    public CompletableFuture<Joke> getRecordsAsync() {
        return CompletableFuture.completedFuture(externalFeignClient.getRandomJoke());
    }

}
