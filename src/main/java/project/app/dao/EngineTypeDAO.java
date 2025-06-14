package project.app.dao;

import project.app.model.EngineType;
import project.app.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EngineTypeDAO {
    public List<EngineType> getAllEngineTypes() throws SQLException {
        List<EngineType> engineTypes = new ArrayList<>();
        String sql = "SELECT engine_id, name FROM engines ORDER BY name";

        try (Connection connection = DatabaseConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                engineTypes.add(new EngineType(resultSet.getInt("engine_id"), resultSet.getString("name")));
            }
        }
        return engineTypes;
    }
}