# Liyah-s-API-Project 
This project implements a RESTful Smart Campus API using Java, JAX-RS (Jersey), and Grizzly. It manages Rooms, Sensors, and Sensor Readings, simulating a real-world campus monitoring system.
# Smart Campus REST API
##  Overview

This project implements a **RESTful Smart Campus API** using Java, JAX-RS (Jersey), and Grizzly.
It manages **Rooms**, **Sensors**, and **Sensor Readings**, simulating a real-world campus monitoring system.

The API supports:

* Room lifecycle management
* Sensor registration and validation
* Historical sensor readings
* Advanced error handling
* Request/response logging

---

##  Technologies Used

* Java
* JAX-RS (Jersey)
* Grizzly HTTP Server
* Maven
* Postman

---

##  How to Run the Project

1. Open the project in IntelliJ IDEA
2. Ensure Maven dependencies are loaded
3. Run `Main.java`
4. Server will start at:

```
http://localhost:8080/api/v2/
```

---

##  Discovery Endpoint

```
GET /api/v2/
```

Returns API metadata and available endpoints.

---

##  API Endpoints

###  Rooms

* `GET /rooms` → Get all rooms
* `POST /rooms` → Create a room
* `GET /rooms/{id}` → Get room details
* `DELETE /rooms/{id}` → Delete room (only if no sensors)

---

###  Sensors

* `GET /sensors` → Get all sensors
* `GET /sensors?type=temperature` → Filter sensors
* `POST /sensors` → Create sensor
* `GET /sensors/{id}` → Get sensor

---

###  Sensor Readings

* `GET /sensors/{id}/readings` → Get readings
* `POST /sensors/{id}/readings` → Add reading

---

##  Sample cURL Commands

### Create Room

```
curl -X POST http://localhost:8080/api/v2/rooms \
-H "Content-Type: application/json" \
-d '{"id":"R1","name":"Room1","capacity":50}'
```

### Get Rooms

```
curl http://localhost:8080/api/v2/rooms
```

### Create Sensor

```
curl -X POST http://localhost:8080/api/v2/sensors \
-H "Content-Type: application/json" \
-d '{"id":"S1","type":"temperature","status":"ACTIVE","roomId":"R1"}'
```

### Filter Sensors

```
curl http://localhost:8080/api/v2/sensors?type=temperature
```

### Add Reading

```
curl -X POST http://localhost:8080/api/v2/sensors/S1/readings \
-H "Content-Type: application/json" \
-d '{"id":"R1","timestamp":1710000000,"value":25.5}'
```

---

#  REPORT ANSWERS

---

## Part 1

### Q1: Lifecycle of JAX-RS Resource

By default, JAX-RS creates a new instance of a resource class per request. This prevents shared state issues and avoids concurrency problems when using in-memory data structures like HashMaps.

### Q2: Hypermedia (HATEOAS)

Hypermedia allows APIs to provide links within responses. This helps clients dynamically navigate the API instead of relying on hardcoded URLs, improving flexibility and usability.

---

## Part 2

### Q1: IDs vs Full Objects

Returning only IDs reduces payload size but requires additional client requests. Returning full objects increases bandwidth but simplifies client-side processing.

### Q2: Idempotency of DELETE

DELETE is idempotent. Repeating the same DELETE request results in the same final state. If the room is already deleted, subsequent calls return 404 without changing the system.

---

## Part 3

### Q1: @Consumes(JSON)

If a client sends data in another format, JAX-RS returns an error (415 Unsupported Media Type). This ensures consistent data handling.

### Q2: QueryParam vs PathParam

Query parameters are better for filtering because they are optional and flexible. Path parameters are more suited for identifying specific resources.

---

## Part 4

### Q1: Sub-Resource Locator Benefits

It separates logic into smaller classes, improving readability, scalability, and maintainability. It avoids large, complex controller classes.

### Q2: Data Consistency

When a new reading is added, the sensor’s `currentValue` is updated to ensure consistency between historical data and current state.

---

## Part 5

### Q1: 409 Conflict

Used when trying to delete a room that still contains sensors.

### Q2: Why 422 instead of 404

422 is more accurate because the request is valid, but the referenced resource inside the request does not exist.

### Q3: 403 Forbidden

Used when a sensor in MAINTENANCE state receives a reading.

### Q4: Stack Trace Risk

Exposing stack traces reveals internal structure, class names, and logic, which attackers can exploit.

### Q5: Logging Filters

Filters centralize logging logic, avoiding repetition and keeping resource classes clean.


##  Video Demonstration

(Upload your video to Blackboard)


## Final Notes

* No database is used (in-memory storage with HashMap)
* Built strictly using JAX-RS (no Spring Boot)
* Designed to follow RESTful principles

## Liyah
