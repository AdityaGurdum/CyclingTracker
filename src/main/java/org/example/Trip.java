package org.example;
import static org.example.Test.getRouteDistanceBetweenPlaces;

public class Trip {
    private int tripId;
    private String startPoint;
    private String endPoint;
    private double distance;
    private String tripDate;
    private String tripTime;

    public Trip(int tripId, String startPoint, String endPoint, String tripDate, String tripTime) throws Exception {
        this.tripId = tripId;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distance = getRouteDistanceBetweenPlaces(startPoint, endPoint);
        this.tripDate = tripDate;
        this.tripTime = tripTime;
    }

    // Getters and Setters
    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate)
    {
        this.tripDate = tripDate;
    }
    public String getTripTime()
    {
            return tripTime;
    }
    public void setTripTime(String tripTime)
    {
            this.tripTime = tripTime;
    }
}