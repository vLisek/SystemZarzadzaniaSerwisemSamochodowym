package project.app.dao;

import project.app.model.Brand;
import project.app.model.Model;
import project.app.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModelDAO {
    private final BrandDAO brandDAO = new BrandDAO();

    public List<Model> getModelsByBrand(Brand brand) throws SQLException {
        return getModelsByBrandId(brand.getId());
    }

    public List<Model> getModelsByBrandId(int brandId) throws SQLException {
        List<Model> models = new ArrayList<>();
        String sql = "SELECT model_id, name, brand_id FROM models WHERE brand_id = ?";

        Brand brand = brandDAO.getBrandById(brandId);

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, brandId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                models.add(new Model(
                        resultSet.getInt("model_id"),
                        resultSet.getString("name"),
                        brand
                ));
            }
        }
        return models;
    }
}
