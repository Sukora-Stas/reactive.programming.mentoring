package com.epam.jmp.reactive.programming.mentoring.api;

import com.epam.jmp.reactive.programming.mentoring.model.Sports;
import com.epam.jmp.reactive.programming.mentoring.repository.SportsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/sports")
@RequiredArgsConstructor
public class SportsController {

    private final SportsRepository repository;

    @GetMapping
    public Flux<Sports> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Sports> getAllData(@PathVariable String id) {
        return repository.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable String id) {
        return repository.deleteById(id);
    }
}
