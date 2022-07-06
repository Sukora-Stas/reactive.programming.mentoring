package com.epam.jmp.reactive.programming.mentoring.repository;

import com.epam.jmp.reactive.programming.mentoring.model.Sports;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SportsRepository extends ReactiveMongoRepository<Sports, String> {

    Mono<Boolean> existsByName(String name);

    Mono<Sports> findByName(String name);

}
