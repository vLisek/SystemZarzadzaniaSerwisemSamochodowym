package project.app.dao;

import project.app.model.DriveType;
import project.app.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriveTypeDAO {
    public List<DriveType> getAllDriveTypes() throws SQLException {
        List<DriveType> driveTypes = new ArrayList<>();
        String sql = "SELECT drive_id, name FROM drive ORDER BY name";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                driveTypes.add(new DriveType(
                        resultSet.getInt("drive_id"),
                        resultSet.getString("name")
                ));
            }
        }
        return driveTypes;
    }
}
