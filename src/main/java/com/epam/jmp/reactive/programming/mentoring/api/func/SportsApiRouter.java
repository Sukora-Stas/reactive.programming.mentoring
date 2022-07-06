package com.epam.jmp.reactive.programming.mentoring.api.func;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class SportsApiRouter {

    public RouterFunction<ServerResponse> sportsRouterFunction(SportsApiHandler sportsApiHandler) {
        return nest(
                path("/api/v2/sports"),
                route(
                        GET("/{id}")
                                .and(accept(MediaType.APPLICATION_JSON))
                                .and(contentType(MediaType.APPLICATION_JSON)),
                        sportsApiHandler::getById));
    }
}
