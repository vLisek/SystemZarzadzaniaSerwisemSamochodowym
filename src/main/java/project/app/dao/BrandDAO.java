package project.app.dao;

import project.app.model.Brand;
import project.app.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrandDAO {
    public static List<Brand> getAllBrands() throws SQLException {
        List<Brand> brands = new ArrayList<>();
        String sql = "SELECT brand_id, name FROM brands ORDER BY name";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                brands.add(new Brand(
                        resultSet.getInt("brand_id"),
                        resultSet.getString("name")
                ));
            }
        }
        return brands;
    }

    public Brand getBrandById(int id) throws SQLException {
        String sql = "SELECT brand_id, name FROM brands WHERE brand_id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Brand(rs.getInt("brand_id"), rs.getString("name"));
            }
        }
        return null; // albo throw new SQLException("Brand not found");
    }

}
