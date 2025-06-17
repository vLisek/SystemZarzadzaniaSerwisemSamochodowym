package project.app.dao;

import project.app.model.*;
import project.app.utils.DatabaseConnector;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();

        String sql = """
                    SELECT
                      o.id AS order_id, o.deadline, o.status, o.total_cost, o.description,
                      c.customer_id, c.first_name AS customer_first_name, c.last_name AS customer_last_name,
                      b.name AS brand,
                      m.name AS model,
                      v.vehicle_id, v.production_year,
                      e.employee_id, e.first_name AS employee_first_name, e.last_name AS employee_last_name,
                      s.id, s.name AS service_name, s.price AS service_price,
                      p.part_id, p.name AS part_name, p.unit_price
                  FROM orders o
                  JOIN customers c ON o.customer_id = c.customer_id
                  JOIN vehicles v ON o.vehicle_id = v.vehicle_id
                  JOIN brands b ON v.brand_id = b.brand_id
                  JOIN models m ON v.model_id = m.model_id
                  JOIN employees e ON o.employee_id = e.employee_id
                  JOIN services s ON o.service_id = s.id
                  JOIN parts p ON o.part_id = p.part_id;
                """;

        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Customer customer = new Customer(rs.getInt("customer_id"), rs.getString("customer_first_name"), rs.getString("customer_last_name"));

                VehicleDisplay vehicle = new VehicleDisplay(rs.getInt("vehicle_id"), rs.getString("brand"), rs.getString("model"), rs.getInt("production_year"));

                Employee employee = new Employee(rs.getInt("employee_id"), rs.getString("employee_first_name"), rs.getString("employee_last_name"));

                Service service = new Service(rs.getInt("id"), rs.getString("service_name"), rs.getDouble("service_price"));

                Parts part = new Parts(rs.getInt("part_id"), rs.getString("part_name"), rs.getDouble("unit_price"));

                Order order = new Order(rs.getInt("order_id"), customer, vehicle, employee, service, part, rs.getDate("deadline").toLocalDate(), rs.getString("status"), rs.getString("description"), rs.getDouble("total_cost"));

                orders.add(order);
            }
        }

        return orders;
    }

    public void addOrder(Order order) throws SQLException {
        String sql = """
                INSERT INTO orders (customer_id, vehicle_id, employee_id,
                                    service_id, part_id, used_part_quantity, deadline, status,
                                    description, total_cost)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, order.getCustomer().getCustomerId());
            stmt.setInt(2, order.getVehicle().getVehicleId());
            stmt.setInt(3, order.getEmployee().getEmployeeId());
            stmt.setInt(4, order.getService().getId());
            stmt.setInt(5, order.getPart().getPartId());

            stmt.setInt(6, order.getUsedPartQuantity());

            stmt.setDate(7, Date.valueOf(order.getDeadline()));
            stmt.setString(8, order.getStatus());
            stmt.setString(9, order.getDescription());
            stmt.setDouble(10, order.getTotalCost());

            stmt.executeUpdate();
        }
    }


    public boolean vehicleHasActiveOrder(int vehicleId) throws SQLException {
        String sql = """
                    SELECT COUNT(*) FROM orders
                    WHERE vehicle_id = ? AND status != 'Zakończone'
                """;

        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vehicleId);
            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    public boolean employeeHasActiveOrder(int employeeId) throws SQLException {
        String sql = """
                    SELECT COUNT(*) FROM orders
                    WHERE employee_id = ? AND status != 'Zakończone'
                """;

        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    public List<Order> getAllOrdersCalendar() throws SQLException {
        List<Order> orders = new ArrayList<>();

        String sql = "SELECT id AS order_id, description, deadline, status FROM orders WHERE deadline IS NOT NULL";

        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                String description = rs.getString("description");
                Date deadlineDate = rs.getDate("deadline");
                LocalDate deadline = deadlineDate != null ? deadlineDate.toLocalDate() : null;
                String status = rs.getString("status");

                orders.add(new Order(orderId, description, deadline, status));
            }

        }

        return orders;
    }

    public void deleteOrder(int orderId) throws SQLException {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        }
    }


    public void updateOrderStatus(int orderId, String newStatus) throws SQLException {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setInt(2, orderId);
            stmt.executeUpdate();
        }
    }
}