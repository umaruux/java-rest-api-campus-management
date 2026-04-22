package com.smartcampus.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.HashMap;
import java.util.Map;

@Path("/")
public class DiscoveryResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getApiInfo() {

        Map<String, Object> response = new HashMap<>();

        response.put("version", "v2");
        response.put("developer", "Aliyah");
        response.put("contact", "Aliyah@smartcampus.com");

        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("rooms", "/api/v2/rooms");
        endpoints.put("sensors", "/api/v2/sensors");

        response.put("endpoints", endpoints);

        return response;
    }
}