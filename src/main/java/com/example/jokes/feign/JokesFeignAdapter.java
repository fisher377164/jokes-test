package com.example.jokes.feign;

import com.example.jokes.dto.Joke;

import java.util.concurrent.CompletableFuture;

public interface JokesFeignAdapter {
    CompletableFuture<Joke> getRecordsAsync();
}
