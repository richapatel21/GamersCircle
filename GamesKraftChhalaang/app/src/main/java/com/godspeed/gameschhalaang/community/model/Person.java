package com.godspeed.gameschhalaang.community.model;


public class Person {
    private int userId;
    private int maxDistance;
    private double latitude;
    private double longitude;
    private String name;

    public Person(int userId, int maxDistance, double latitude, double longitude) {
        this.userId = userId;
        this.maxDistance = maxDistance;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getUserId() {
        return userId;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }
}

