package project.app.dao;

import project.app.utils.DatabaseConnector;
import project.app.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public void insertCustomer(String firstName, String lastName, String phone, String email, int clientTypeId, String companyName) throws SQLException {
        String sql = "INSERT INTO customers (first_name, last_name, phone, email, client_type_id, company_name) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, phone);
            stmt.setString(4, email);
            stmt.setInt(5, clientTypeId);
            stmt.setString(6, companyName);

            stmt.executeUpdate();
        }
    }

    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> list = new ArrayList<>();
        String sql = """
            SELECT c.customer_id, c.first_name, c.last_name, c.phone, c.email, ct.client_type_name, c.company_name
            FROM customers c
            JOIN client_types ct ON c.client_type_id = ct.client_type_id
            """;

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("client_type_name"),
                        rs.getString("company_name")
                );
                customer.setCustomerId(rs.getInt("customer_id"));
                list.add(customer);
            }
        }
        return list;
    }

    public void deleteCustomer(int customerId) throws SQLException {
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.executeUpdate();
        }
    }
}
