package com.group11.moviebooking.repository;

import com.group11.moviebooking.util.BasicImpl;
import com.group11.moviebooking.util.ConnectionPool;
import com.group11.moviebooking.util.ConnectionPoolImpl;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class BookingRepositoryImpl extends BasicImpl implements BookingRepository {
    private final ConnectionPool connectionPool;

    public BookingRepositoryImpl() {
        super("tblbookings");
        this.connectionPool = ConnectionPoolImpl.getInstance();
    }

    @Override
    public boolean createBooking(long customer_id, int showtime_id, int total_price) {
        String sql = "INSERT INTO tblbookings(customer_id, showtime_id, total_price) " +
                "VALUES (?,?,?)";

        try (Connection connection = connectionPool.getConnection("BookingRepositoryImpl.createBooking");
             PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setLong(1, customer_id);
            pre.setInt(2, showtime_id);
            pre.setInt(3, total_price);
            return pre.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean ceateBookingSeat(int booking_id, int seat_id) {
        String sql = "INSERT INTO tblbooking_seats(booking_id, seat_id) " +
                "VALUES(?,?)";
        try (Connection connection = connectionPool.getConnection("BookingRepositoryImpl.ceateBookingSeat");
             PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setLong(1, booking_id);
            pre.setInt(2, seat_id);
            pre.executeUpdate();
            this.con.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getLastBookingId() {
        int booking_id = 0;
        String sql = "SELECT MAX(booking_id)  FROM tblbookings;";
        try (Connection connection = connectionPool.getConnection("BookingRepositoryImpl.getLastBookingId");
             PreparedStatement pre = connection.prepareStatement(sql)) {
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                booking_id = rs.getInt(1);
            }
            return booking_id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
