package project.app.dao;

import project.app.model.VehicleDisplay;
import project.app.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<VehicleDisplay> getAllVehiclesForDisplay() throws SQLException {
        List<VehicleDisplay> list = new ArrayList<>();
        String sql = """
            SELECT
                b.name AS brand,
                m.name AS model,
                bt.name AS body_type,
                ft.name AS fuel_type,
                e.name AS engine_type,
                d.name AS drive_type,
                v.engine_capacity,
                v.production_year,
                v.mileage
            FROM vehicles v
            JOIN brands b ON v.brand_id = b.brand_id
            JOIN models m ON v.model_id = m.model_id
            JOIN body_types bt ON v.body_type_id = bt.body_type_id
            JOIN fuel_types ft ON v.fuel_type_id = ft.fuel_type_id
            JOIN engines e ON v.engine_type_id = e.engine_id
            JOIN drive d ON v.drive_type_id = d.drive_id
        """;

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {
                list.add(new VehicleDisplay(
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getString("body_type"),
                        resultSet.getString("fuel_type"),
                        resultSet.getString("engine_type"),
                        resultSet.getString("drive_type"),
                        resultSet.getInt("engine_capacity"),
                        resultSet.getInt("production_year"),
                        resultSet.getInt("mileage")
                ));
            }
        }

        return list;
    }
}

