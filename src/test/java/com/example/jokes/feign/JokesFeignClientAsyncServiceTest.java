package com.example.jokes.feign;

import com.example.jokes.dto.Joke;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JokesFeignClientAsyncServiceTest {

    @Mock
    private JokesFeignClient externalFeignClient;

    @InjectMocks
    private JokesFeignClientAsyncService jokesFeignClientAsyncService;

    @Test
    void getRecordsAsync() throws ExecutionException, InterruptedException {
        final var expected = new Joke("type", "setup", "punchline", 123L);

        when(externalFeignClient.getRandomJoke()).thenReturn(expected);

        final var actualJokes = jokesFeignClientAsyncService.getRecordsAsync();

        verify(externalFeignClient, times(1)).getRandomJoke();

        assertEquals(expected, actualJokes.get());
    }
}
