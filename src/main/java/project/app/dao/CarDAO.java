package project.app.dao;

import project.app.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarDAO {
    public void insertVehicle(int bodyTypeId, int brandId, int modelId, int fuelTypeId,
                              int engineTypeId, int engineCapacity, int driveTypeId,
                              int productionYear, int mileage) throws SQLException {

        String sql = "INSERT INTO vehicles (body_type_id, brand_id, model_id, fuel_type_id, " +
                "engine_type_id, engine_capacity, drive_type_id, production_year, mileage) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bodyTypeId);
            stmt.setInt(2, brandId);
            stmt.setInt(3, modelId);
            stmt.setInt(4, fuelTypeId);
            stmt.setInt(5, engineTypeId);
            stmt.setInt(6, engineCapacity);
            stmt.setInt(7, driveTypeId);
            stmt.setInt(8, productionYear);
            stmt.setInt(9, mileage);

            stmt.executeUpdate();
        }
    }
}

