package project.app.dao;

import project.app.model.Parts;
import project.app.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartsDAO {

    public List<Parts> getAllParts() {
        List<Parts> parts = new ArrayList<>();
        String query = "SELECT * FROM parts";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Parts part = new Parts(
                        rs.getInt("part_id"),
                        rs.getString("producer"),
                        rs.getString("name"),
                        rs.getDouble("unit_price"),
                        rs.getInt("quantity")
                );
                parts.add(part);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parts;
    }

    public void addPart(Parts part) {
        String query = "INSERT INTO parts (producer, name, unit_price, quantity) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, part.getBrand());
            ps.setString(2, part.getName());
            ps.setDouble(3, part.getPrice());
            ps.setInt(4, part.getQuantity());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePart(int partId) {
        String query = "DELETE FROM parts WHERE part_id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, partId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



