package com.smartcampus.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.*;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {

    public static Map<String, Room> rooms = new HashMap<>();

    // 🔹 GET all rooms
    @GET
    public Collection<Room> getAllRooms() {
        return rooms.values();
    }

    // 🔹 GET room by ID
    @GET
    @Path("/{id}")
    public Response getRoom(@PathParam("id") String id) {
        Room room = rooms.get(id);

        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Room not found\"}")
                    .build();
        }

        return Response.ok(room).build();
    }

    // 🔹 POST create room
    @POST
    public Response createRoom(Room room) {

        // 🔥 Improvement: validation (new vs your old project)
        if (room == null || room.getId() == null || room.getName() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"Invalid room data\"}")
                    .build();
        }

        // 🔥 Improvement: prevent duplicates
        if (rooms.containsKey(room.getId())) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("{\"error\":\"Room already exists\"}")
                    .build();
        }

        // 🔥 Improvement: capacity check
        if (room.getCapacity() <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"Capacity must be positive\"}")
                    .build();
        }

        rooms.put(room.getId(), room);

        return Response.status(Response.Status.CREATED)
                .entity(room)
                .build();
    }
    @DELETE
    @Path("/{id}")
    public Response deleteRoom(@PathParam("id") String id) {

        Room room = rooms.get(id);

        // 🔹 Room not found
        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Room not found\"}")
                    .build();
        }

        //  CORE LOGIC (IMPORTANT)
        if (!room.getSensorIds().isEmpty()) {
            throw new RoomNotEmptyException("Room has sensors, cannot delete");
        }

        rooms.remove(id);

        return Response.ok("{\"message\":\"Room deleted successfully\"}").build();
    }
}