package com.example.jokes.feign;

import com.example.jokes.dto.Joke;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "${jokes.external.url}", name = "external")
public interface JokesFeignClient {

    @GetMapping(value = "random_joke", produces = "application/json")
    Joke getRandomJoke();
}
