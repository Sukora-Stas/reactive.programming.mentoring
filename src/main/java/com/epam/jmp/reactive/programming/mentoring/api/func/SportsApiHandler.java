package com.epam.jmp.reactive.programming.mentoring.api.func;

import com.epam.jmp.reactive.programming.mentoring.model.Sports;
import com.epam.jmp.reactive.programming.mentoring.repository.SportsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class SportsApiHandler {

    private final SportsRepository repository;

    public Mono<ServerResponse> getById(ServerRequest request) {
        var id =
                Optional.of(request.pathVariable("id"))
                        .filter(Predicate.not(String::isBlank))
                        .orElseThrow(() -> new ServerWebInputException("Invalid id parametr"));

        return repository
                .findById(id)
                .flatMap(sports -> ServerResponse.ok().bodyValue(sports))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> add(ServerRequest request) {
        return save(request)
                .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage()));
    }

    private Mono<ServerResponse> save(ServerRequest request) {
        var name = getName(request);

        return repository.existsByName(name)
                .flatMap(aBoolean -> {
                    if (aBoolean) {
                        throw new ServerWebInputException("Sports with name: " + name + " already exists");
                    } else {
                        return repository.save(Sports.builder().name(name).build())
                                .flatMap(sports -> ServerResponse.ok().bodyValue(sports));
                    }
                });
    }

    public Mono<ServerResponse> getByName(ServerRequest request) {
        var name = getName(request);

        return repository
                .findByName(name)
                .flatMap(sports -> ServerResponse.ok().bodyValue(sports))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    private String getName(ServerRequest request) {
        return Optional.of(request.pathVariable("name"))
                .filter(Predicate.not(String::isBlank))
                .orElseThrow(() -> new ServerWebInputException("invalid name parameter"));
    }
}
