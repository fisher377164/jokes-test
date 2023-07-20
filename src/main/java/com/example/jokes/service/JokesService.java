package com.example.jokes.service;

import com.example.jokes.config.JokesProperties;
import com.example.jokes.dto.Joke;
import com.example.jokes.feign.JokesFeignClientAsyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class JokesService implements JokesUseCase {

    private final JokesFeignClientAsyncService externalFeignClientAsync;
    private final JokesProperties jokesProperties;

    @Override
    public List<Joke> get(final Integer count) {
        final var pageSize = jokesProperties.getApp().defaultPageSize();
        final var batches = (count / pageSize) + (count % pageSize > 0 ? 1 : 0);

        return IntStream.range(0, batches)
                .mapToObj(i -> {
                    int currentPageSize = i != batches - 1 || count % pageSize == 0 ? pageSize : count % pageSize;
                    return getJokesBatch(currentPageSize);
                })
                .flatMap(Collection::stream)
                .toList();
    }

    List<Joke> getJokesBatch(final Integer count) {
        return IntStream.range(0, count)
                .parallel()
                .mapToObj(i -> externalFeignClientAsync.getRecordsAsync())
                .map(CompletableFuture::join)
                .toList();
    }
}
