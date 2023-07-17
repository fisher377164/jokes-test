package com.example.jokes.service;

import com.example.jokes.dto.Joke;
import com.example.jokes.feign.ExternalFeignClientAsync;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ExternalService {

    private final ExternalFeignClientAsync externalFeignClientAsync;

    public List<Joke> getJokes(final Integer count) {

        int pageSize = 10; // to properties
        int batches = (count / pageSize) + (count % pageSize > 0 ? 1 : 0);

        return IntStream.range(0, batches)
                .parallel()
                .mapToObj(i -> {
                    int lastPageSize = i != batches - 1 ? pageSize : count % pageSize;
                    return getJokeBatch(lastPageSize);
                })
                .flatMap(Collection::stream)
                .toList();
    }

    List<Joke> getJokeBatch(final Integer count) {
        return IntStream.range(0, count)
                .parallel()
                .mapToObj(i -> externalFeignClientAsync.getRecordsAsync())
                .map(CompletableFuture::join)
                .toList();
    }
}
