package com.smartcampus.api;

public class Sensor {

    private String id;
    private String type;
    private String status;
    private double currentValue;
    private String roomId;

    public Sensor() {}

    public Sensor(String id, String type, String status, String roomId) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.roomId = roomId;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getCurrentValue() { return currentValue; }
    public void setCurrentValue(double currentValue) { this.currentValue = currentValue; }

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }
}