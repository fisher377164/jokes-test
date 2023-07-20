package com.example.jokes.service;

import com.example.jokes.dto.Joke;

import java.util.List;

public interface JokesUseCase {
    List<Joke> get(final Integer count);
}
