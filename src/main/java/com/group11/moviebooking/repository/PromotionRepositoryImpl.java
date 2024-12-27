package com.group11.moviebooking.repository;

import com.group11.moviebooking.entity.PromotionEntity;
import com.group11.moviebooking.util.BasicImpl;
import com.group11.moviebooking.util.ConnectionPool;
import com.group11.moviebooking.util.ConnectionPoolImpl;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PromotionRepositoryImpl extends BasicImpl implements PromotionRepository {
    private final ConnectionPool connectionPool;

    public PromotionRepositoryImpl() {
        super("tblpromotion");
        this.connectionPool = ConnectionPoolImpl.getInstance();
    }

    @Override
    public List<PromotionEntity> getAllPromotions() {
        List<PromotionEntity> promotions = new ArrayList<PromotionEntity>();
        String sql = "select * from tblpromotions LIMIT 20";
        try (Connection connection = connectionPool.getConnection("PromotionRepositoryImpl.getAllPromotions");
             PreparedStatement pre = connection.prepareStatement(sql)) {
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    PromotionEntity promotion = new PromotionEntity();
                    promotion.setPromotion_id(rs.getInt("promotion_id"));
                    promotion.setPromotion_name(rs.getString("promotion_name"));
                    promotion.setPromotion_description(rs.getString("promotion_description"));
                    promotions.add(promotion);
                }
            }
            return promotions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
