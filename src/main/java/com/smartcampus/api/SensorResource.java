package com.smartcampus.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.*;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {

    private static Map<String, Sensor> sensors = new HashMap<>();

    // 🔹 POST create sensor
    @POST
    public Response createSensor(Sensor sensor) {

        if (sensor == null || sensor.getId() == null || sensor.getRoomId() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"Invalid sensor data\"}")
                    .build();
        }

        // 🔥 CORE REQUIREMENT: check if room exists
        if (!RoomResource.rooms.containsKey(sensor.getRoomId())) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"Room does not exist\"}")
                    .build();
        }

        // 🔥 improvement: prevent duplicates
        if (sensors.containsKey(sensor.getId())) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("{\"error\":\"Sensor already exists\"}")
                    .build();
        }

        sensors.put(sensor.getId(), sensor);

        // 🔥 LINK sensor to room
        Room room = RoomResource.rooms.get(sensor.getRoomId());
        room.getSensorIds().add(sensor.getId());

        return Response.status(Response.Status.CREATED).entity(sensor).build();
    }

    // 🔹 GET all sensors + filtering
    @GET
    public Collection<Sensor> getSensors(@QueryParam("type") String type) {

        if (type == null) {
            return sensors.values();
        }

        List<Sensor> filtered = new ArrayList<>();

        for (Sensor s : sensors.values()) {
            if (type.equalsIgnoreCase(s.getType())) {
                filtered.add(s);
            }
        }

        return filtered;
    }

    // 🔹 GET sensor by ID
    @GET
    @Path("/{id}")
    public Response getSensor(@PathParam("id") String id) {

        Sensor sensor = sensors.get(id);

        if (sensor == null) {
            throw new LinkedResourceNotFoundException("Room does not exist");
        }

        return Response.ok(sensor).build();
    }

    // 🔹 expose sensors map (for Part 4)
    public static Map<String, Sensor> getSensorsMap() {
        return sensors;
    }
    @Path("/{id}/readings")
    public SensorReadingResource getReadingResource(@PathParam("id") String id) {
        return new SensorReadingResource(id);
    }
}