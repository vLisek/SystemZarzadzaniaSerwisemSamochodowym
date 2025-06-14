package project.app.dao;

import project.app.model.FuelType;
import project.app.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuelTypeDAO {
    public List<FuelType> getAllFuelTypes() throws SQLException {
        List<FuelType> fuelTypes = new ArrayList<>();
        String sql = "SELECT fuel_type_id, name FROM fuel_types ORDER BY name";

        try (Connection connection = DatabaseConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                fuelTypes.add(new FuelType(resultSet.getInt("fuel_type_id"), resultSet.getString("name")));
            }
        }
        return fuelTypes;
    }
}