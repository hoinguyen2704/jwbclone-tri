package com.group11.moviebooking.repository;


import com.group11.moviebooking.model.RevenueDTO;
import com.group11.moviebooking.util.BasicImpl;
import com.group11.moviebooking.util.ConnectionPool;
import com.group11.moviebooking.util.ConnectionPoolImpl;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RevenueImpl extends BasicImpl implements Revenue {
    private final ConnectionPool connectionPool;

    public RevenueImpl() {
        super("Revenue");
        this.connectionPool = ConnectionPoolImpl.getInstance();
    }

    public List<RevenueDTO> findWeekRevenue() {
        List<RevenueDTO> revenueList = new ArrayList<>();
        // Lấy ngày hôm nay và ngày cách đây 7 ngày
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysAgo = today.minusDays(6);

        // Chuyển đổi ngày thành String (ISO format yyyy-MM-dd)
        String startDate = sevenDaysAgo.toString();
        String endDate = today.plusDays(1).toString();
//        String sql = "SELECT show_date, SUM(ticket_price) AS total_revenue " +
//                "FROM tblshowtimes " +
//                "WHERE show_date BETWEEN ? AND ? " +
//                "GROUP BY show_date ORDER BY show_date ASC;";

        String sql = "SELECT " +
                "    DATE(booking_created_at) AS show_date, " +
                "    SUM(total_price) AS total_revenue " +
                "FROM " +
                "    tblbookings " +
                "WHERE " +
                "    booking_created_at BETWEEN ? AND ? " +
                "GROUP BY " +
                "    DATE(booking_created_at) " +
                "ORDER BY " +
                "    show_date ASC;";
        try (Connection connection = connectionPool.getConnection("BookingRepositoryImpl.createBooking");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, startDate);
            statement.setString(2, endDate);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String showDate = resultSet.getString("show_date");
                    double totalRevenue = resultSet.getInt("total_revenue");
                    // Thêm doanh thu vào danh sách
                    revenueList.add(new RevenueDTO(showDate, totalRevenue));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revenueList;
    }
}
