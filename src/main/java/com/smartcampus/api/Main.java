package com.smartcampus.api;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.net.URI;

public class Main {

    public static final String BASE_URI = "http://localhost:8080/api/v2/";

    public static HttpServer startServer() {

        final ResourceConfig rc = new ResourceConfig()
                .packages("com.smartcampus.api")

                // JSON support
                .register(JacksonFeature.class)


                .register(RoomNotEmptyExceptionMapper.class)
                .register(LinkedResourceNotFoundExceptionMapper.class)
                .register(SensorUnavailableExceptionMapper.class)
                .register(GenericExceptionMapper.class)


                .register(LoggingFilter.class);

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) {
        final HttpServer server = startServer();
        System.out.println("Server running at " + BASE_URI);

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            server.shutdownNow();
        }
    }

}