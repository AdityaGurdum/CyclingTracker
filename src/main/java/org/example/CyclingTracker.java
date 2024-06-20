package org.example;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class CyclingTracker {
    public static void main(String[] args) {
        TripDAO tripDAO = new TripDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Cycling Tracker System");
            System.out.println("1. Add Trip");
            System.out.println("2. View Trips");
            System.out.println("3.Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter start point: ");
                    String startPoint = scanner.nextLine();
                    System.out.print("Enter end point: ");
                    String endPoint = scanner.nextLine();
                    System.out.print("Enter trip date (YYYY-MM-DD): ");
                    String tripDate=scanner.nextLine();
                    System.out.print("Enter trip time (HH:MM:SS): ");
                    String tripTime=scanner.nextLine();
                    System.out.print("Enter Trip Id: ");
                    int tripId=scanner.nextInt();
                    Trip newTrip = null;
                    try {
                        newTrip = new Trip(tripId,startPoint, endPoint, tripDate, tripTime);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    tripDAO.addTrip(newTrip);
                    System.out.println("Trip added successfully.");
                    break;

                case 2:
                    System.out.println("Trip List:");
                    List<Trip> trips = tripDAO.getAllTrips();
                    for (Trip trip : trips) {
                        System.out.println("trip Id:"+trip.getTripId()+","+trip.getStartPoint() + " to " + trip.getEndPoint() + " - " +
                                trip.getDistance() + " km on " + trip.getTripDate() + " at " + trip.getTripTime());
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}