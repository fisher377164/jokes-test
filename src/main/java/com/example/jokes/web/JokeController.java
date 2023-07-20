package com.example.jokes.web;

import com.example.jokes.dto.Joke;
import com.example.jokes.service.JokesUseCase;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/jokes")
public class JokeController {

    private final JokesUseCase jokesUseCase;
    @GetMapping
    public List<Joke> jokes(@RequestParam(defaultValue = "5") @Min(1) @Max(value = 100, message = "За один раз можна отримати не більше 100 шуток.") final Integer count) {
        return jokesUseCase.get(count);
    }
}
