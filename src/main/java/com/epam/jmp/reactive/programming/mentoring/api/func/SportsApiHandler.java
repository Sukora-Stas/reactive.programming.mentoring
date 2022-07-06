package com.epam.jmp.reactive.programming.mentoring.api.func;

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
}
