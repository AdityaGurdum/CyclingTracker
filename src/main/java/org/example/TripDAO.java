package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TripDAO {
    public void addTrip(Trip trip) {
        String sql = "INSERT INTO trips2 (trip_id, start_point, end_point, distance, trip_date, trip_time) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Assuming trip_date and trip_time are of SQL types DATE and TIME
            pstmt.setInt(1, trip.getTripId());
            pstmt.setString(2, trip.getStartPoint());
            pstmt.setString(3, trip.getEndPoint());
            pstmt.setDouble(4, trip.getDistance());

            // Convert strings to SQL Date and Time
            pstmt.setString(5, trip.getTripDate()); // trip_date in 'yyyy-MM-dd' format
            pstmt.setString(6, trip.getTripTime()); // trip_time in 'HH:mm:ss' format

            System.out.println("Executing query: " + pstmt);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Trip added successfully!");
            } else {
                System.out.println("Failed to add the trip.");
            }

        } catch (SQLException e) {
            System.out.println("Error occurred while adding the trip: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Trip> getAllTrips() {
        List<Trip> trips = new ArrayList<>();
        String sql = "SELECT * FROM trips2";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
                Trip trip = new Trip(
                        rs.getInt("trip_id"),
                        rs.getString("start_point"),
                        rs.getString("end_point"),
                        rs.getString("trip_date"),
                        rs.getString("trip_time")
                );
                trip.setDistance(rs.getDouble("distance"));
                trips.add(trip);
            }

            System.out.println("Number of rows retrieved: " + rowCount);

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return trips;
    }
}
