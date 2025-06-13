package project.app.dao;

import project.app.model.Service;
import project.app.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {

    public static List<Service> getAllServices() throws SQLException {
        List<Service> list = new ArrayList<>();
        String sql = "SELECT * FROM services";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Service s = new Service(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("duration")
                );
                list.add(s);
            }
        }

        return list;
    }

    public static void insertService(Service service) throws SQLException {
        String sql = "INSERT INTO services (name, price, duration) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, service.getName());
            ps.setDouble(2, service.getPrice());
            ps.setInt(3, service.getDuration());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    service.setId(rs.getInt(1));
                }
            }
        }
    }

    public static void updateService(Service service) throws SQLException {
        String sql = "UPDATE services SET name = ?, price = ?, duration = ? WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, service.getName());
            ps.setDouble(2, service.getPrice());
            ps.setInt(3, service.getDuration());
            ps.setInt(4, service.getId());

            ps.executeUpdate();
        }
    }

    public static void deleteService(int id) throws SQLException {
        String sql = "DELETE FROM services WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}

