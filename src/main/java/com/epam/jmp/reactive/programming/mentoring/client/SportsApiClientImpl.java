package com.epam.jmp.reactive.programming.mentoring.client;

import com.epam.jmp.reactive.programming.mentoring.model.Sports;
import com.epam.jmp.reactive.programming.mentoring.model.SportsData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
@Slf4j
public class SportsApiClientImpl implements SportsApiClient {

    private final WebClient webClient;

    @Override
    public Flux<Sports> getAllSportsData() {
        Flux<Sports> sportsFlux =
                webClient
                        .get()
                        .uri("/sports")
                        .exchangeToMono(clientResponse -> clientResponse.bodyToMono(SportsData.class))
                        .doOnError(err -> log.error("error getting response", err))
                        .flatMapIterable(SportsData::getData)
                        .doOnNext(data -> log.info("sports data: {}", data));

        sportsFlux.doOnRequest(value -> log.info("request of {}", value))
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        System.out.println("Subscribed");
                        request(20);
                    }

                    @Override
                    protected void hookOnNext(Sports value) {
                        log.info("Next: {}", value);
                        request(20);
                    }
                });
        return sportsFlux;
    }
}
