# Smart Campus REST API

A Java-based RESTful API for managing campus rooms, sensors, and sensor readings.
This project implements a RESTful Smart Campus API using Java, JAX-RS (Jersey), and Grizzly. It manages Rooms, Sensors, and Sensor Readings, simulating a real-world campus monitoring system.

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
curl -X POST http://localhost:8080/api/v1/rooms \
-H "Content-Type: application/json" \
-d '{"id":"R1","name":"Room1","capacity":50}'
```

### Get Rooms

```
curl http://localhost:8080/api/v1/rooms
```

### Create Sensor

```
curl -X POST http://localhost:8080/api/v1/sensors \
-H "Content-Type: application/json" \
-d '{"id":"S1","type":"temperature","status":"ACTIVE","roomId":"R1"}'
```

### Filter Sensors

```
curl http://localhost:8080/api/v1/sensors?type=temperature
```

### Add Reading

```
curl -X POST http://localhost:8080/api/v1/sensors/S1/readings \
-H "Content-Type: application/json" \
-d '{"id":"R1","timestamp":1710000000,"value":25.5}'
```


