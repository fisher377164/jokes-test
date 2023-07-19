package com.example.jokes.service;

import com.example.jokes.config.JokesProperties;
import com.example.jokes.dto.Joke;
import com.example.jokes.feign.JokesFeignClientAsyncService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JokesServiceTest {

    @Mock
    private JokesFeignClientAsyncService externalFeignClientAsync;
    @Mock
    private JokesProperties jokesProperties;

    @InjectMocks
    private JokesService jokesService;

    @Test
    void getJokesWithEqualBatches() {
        final var appProperties = new JokesProperties.AppProperties(2);
        final var joke = new Joke("type", "setup", "punchline", 123L);

        when(jokesProperties.getApp()).thenReturn(appProperties);
        when(externalFeignClientAsync.getRecordsAsync()).thenReturn(CompletableFuture.completedFuture(joke));

        final var actualJokes = jokesService.get(4);

        verify(externalFeignClientAsync, times(4)).getRecordsAsync();

        assertEquals(4, actualJokes.size());
    }

    @Test
    void getJokesWithUnEqualBatches() {
        final var appProperties = new JokesProperties.AppProperties(3);
        final var value = new Joke("type", "setup", "punchline", 123L);

        when(jokesProperties.getApp()).thenReturn(appProperties);
        when(externalFeignClientAsync.getRecordsAsync()).thenReturn(CompletableFuture.completedFuture(value));

        final var actualJokes = jokesService.get(5);

        verify(externalFeignClientAsync, times(5)).getRecordsAsync();

        assertEquals(5, actualJokes.size());
    }
}
