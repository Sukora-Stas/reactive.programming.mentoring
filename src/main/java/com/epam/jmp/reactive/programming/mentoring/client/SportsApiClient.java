package com.epam.jmp.reactive.programming.mentoring.client;

import com.epam.jmp.reactive.programming.mentoring.model.Sports;
import reactor.core.publisher.Flux;

public interface SportsApiClient {

    Flux<Sports> getAllSportsData();
}
