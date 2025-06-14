package project.app.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import project.app.dao.EmployeeDAO;
import project.app.model.Employee;
import project.app.utils.AlertUtils;

import java.util.List;

public class EmployeesController {

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee, Integer> idColumn;

    @FXML
    private TableColumn<Employee, String> firstNameColumn;

    @FXML
    private TableColumn<Employee, String> lastNameColumn;

    @FXML
    private TableColumn<Employee, String> phoneColumn;

    @FXML
    private TableColumn<Employee, String> positionColumn;

    @FXML
    private TableColumn<Employee, String> loginColumn;

    private EmployeeDAO employeeDAO;
    private ObservableList<Employee> employeeList;

    @FXML
    private void initialize() {
        employeeDAO = new EmployeeDAO();
        employeeList = FXCollections.observableArrayList();

        setupTableColumns();

        loadEmployeeData();
    }

    private void setupTableColumns() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
    }

    private void loadEmployeeData() {
        try {
            List<Employee> employees = employeeDAO.getAllEmployees();
            employeeList.clear();
            employeeList.addAll(employees);
            employeeTable.setItems(employeeList);

        } catch (Exception e) {
            AlertUtils.showError("Error", "Nie udało się załadować danych pracowników: " + e.getMessage());
        }
    }
}
