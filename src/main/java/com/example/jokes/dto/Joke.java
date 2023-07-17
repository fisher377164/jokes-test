package com.example.jokes.dto;

public record Joke(String type,
                   String setup,
                   String punchline,
                   Long id) {
}
