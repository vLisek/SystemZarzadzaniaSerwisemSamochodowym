package project.app.dao;

import project.app.model.BodyType;
import project.app.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BodyTypeDAO {
    public List<BodyType> getAllBodyTypes() throws SQLException {
        List<BodyType> bodyTypes = new ArrayList<>();
        String sql = "SELECT body_type_id, name FROM body_types ORDER BY name";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                bodyTypes.add(new BodyType(
                        rs.getInt("body_type_id"),
                        rs.getString("name")
                ));
            }
        }
        return bodyTypes;
    }
}
