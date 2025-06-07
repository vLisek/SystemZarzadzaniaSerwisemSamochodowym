package project.app.dao;

import project.app.model.ClientType;
import project.app.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientTypeDAO {
    public List<ClientType> getAllClientTypes() throws SQLException {
        List<ClientType> list = new ArrayList<>();
        String sql = "SELECT client_type_id, client_type_name FROM client_types";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new ClientType(rs.getInt("client_type_id"), rs.getString("client_type_name")));
            }
        }
        return list;
    }
}