package project.app.dao;

import project.app.model.Employee;
import project.app.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        String query = "SELECT e.employee_id, e.first_name, e.last_name, e.phone_number, e.position, l.login FROM employees e JOIN logins l ON e.employee_id = l.employee_id ORDER BY e.employee_id";

        try {
            Connection connection = DatabaseConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setPhoneNumber(resultSet.getString("phone_number"));
                employee.setPosition(resultSet.getString("position"));
                employee.setLogin(resultSet.getString("login"));

                employees.add(employee);
            }

        } catch (SQLException e) {
            System.err.println("Błąd podczas pobierania danych pracowników: " + e.getMessage());
        }

        return employees;
    }
}