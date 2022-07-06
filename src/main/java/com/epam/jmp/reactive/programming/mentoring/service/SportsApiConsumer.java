package com.epam.jmp.reactive.programming.mentoring.service;

import com.epam.jmp.reactive.programming.mentoring.client.SportsApiClient;
import com.epam.jmp.reactive.programming.mentoring.repository.SportsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SportsApiConsumer implements SmartLifecycle {

    private final SportsApiClient apiClient;
    private final SportsRepository sportsRepository;

    @Override
    public void start() {
        sportsRepository.saveAll(apiClient.getAllSportsData()).blockLast();
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
