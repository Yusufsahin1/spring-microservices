package com.e_library.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions.lb;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.web.servlet.function.RequestPredicates.path;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouterFunction<ServerResponse> bookServiceRoute() {
		return route("book-service")
				.route(path("/api/v1/book/**"), http())
				.filter(lb("book-service"))
				.build();
	}

	@Bean
	public RouterFunction<ServerResponse> libraryServiceRoute() {
		return route("library-service")
				.route(path("/api/v1/library/**"), http())
				.filter(lb("library-service"))
				.build();
	}
}
