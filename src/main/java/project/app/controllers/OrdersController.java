package project.app.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import project.app.dao.*;
import project.app.model.*;
import project.app.utils.FxUtils;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class OrdersController {

    @FXML private TableView<Order> ordersTable;
    @FXML private TableColumn<Order, String> clientColumn;
    @FXML private TableColumn<Order, String> vehicleColumn;
    @FXML private TableColumn<Order, String> employeeColumn;
    @FXML private TableColumn<Order, LocalDate> deadlineColumn;
    @FXML private TableColumn<Order, String> statusColumn;
    @FXML private TableColumn<Order, Double> totalCostColumn;

    @FXML private ComboBox<Customer> clientComboBox;
    @FXML private ComboBox<VehicleDisplay> vehicleComboBox;
    @FXML private ComboBox<Employee> employeeComboBox;
    @FXML private ComboBox<Service> serviceComboBox;
    @FXML private ComboBox<Parts> partComboBox;
    @FXML private ComboBox<String> statusComboBox;
    @FXML private DatePicker deadlinePicker;
    @FXML private TextArea descriptionArea;

    @FXML private Label infoLabel;

    private final OrderDAO orderDAO = new OrderDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final CarDAO vehicleDAO = new CarDAO();
    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    private final ServiceDAO serviceDAO = new ServiceDAO();
    private final PartsDAO partsDAO = new PartsDAO();

    public void initialize() {
        setupTableColumns();
        loadAllComboBoxData();
        refreshOrdersTable();
    }

    private void setupTableColumns() {
        clientColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCustomer().getFullName()));
        vehicleColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVehicle().toString()));
        employeeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmployee().getFullName()));
        deadlineColumn.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        totalCostColumn.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
    }

    private void loadAllComboBoxData() {
        try {
            clientComboBox.setItems(FXCollections.observableArrayList(customerDAO.getAllCustomers()));
            vehicleComboBox.setItems(FXCollections.observableArrayList(vehicleDAO.getAllVehiclesForDisplay()));
            employeeComboBox.setItems(FXCollections.observableArrayList(employeeDAO.getAllEmployees()));
            serviceComboBox.setItems(FXCollections.observableArrayList(serviceDAO.getAllServices()));
            partComboBox.setItems(FXCollections.observableArrayList(partsDAO.getAllParts()));
            statusComboBox.setItems(FXCollections.observableArrayList("Nowe", "W trakcie", "Zakończone"));
        } catch (SQLException e) {
            showError("Błąd ładowania danych: " + e.getMessage());

        }
    }

    private void refreshOrdersTable() {
        try {
            List<Order> orders = orderDAO.getAllOrders();
            ordersTable.setItems(FXCollections.observableArrayList(orders));
        } catch (Exception e) {
            showError("Błąd ładowania zleceń: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void addOrder() {
        try {
            Order newOrder = createOrderFromForm();
            if (newOrder == null) return;

            if (orderDAO.vehicleHasActiveOrder(newOrder.getVehicle().getVehicleId())) {
                showError("Wybrany pojazd ma już aktywne zlecenie.");
                return;
            }

            if (orderDAO.employeeHasActiveOrder(newOrder.getEmployee().getEmployeeId())) {
                showError("Pracownik prowadzi już inne zlecenie.");
                return;
            }

            orderDAO.addOrder(newOrder);
            refreshOrdersTable();
            clearForm();
            showInfo("Zlecenie dodane pomyślnie.");
        } catch (SQLException e) {
            showError("Błąd zapisu do bazy: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Order createOrderFromForm() {
        Customer customer = clientComboBox.getValue();
        VehicleDisplay vehicle = vehicleComboBox.getValue();
        Employee employee = employeeComboBox.getValue();
        Service service = serviceComboBox.getValue();
        Parts part = partComboBox.getValue();
        LocalDate deadline = deadlinePicker.getValue();
        String status = statusComboBox.getValue();
        String description = descriptionArea.getText();

        if (customer == null || vehicle == null || employee == null || service == null || part == null ||
                deadline == null || status == null || description == null || description.isBlank()) {
            showError("Wszystkie pola są wymagane.");
            return null;
        }

        double totalCost = service.getPrice() + part.getUnitPrice();

        return new Order(
                0,
                customer,
                vehicle,
                employee,
                service,
                part,
                deadline,
                status,
                description,
                totalCost
        );
    }

    private void clearForm() {
        clientComboBox.getSelectionModel().clearSelection();
        vehicleComboBox.getSelectionModel().clearSelection();
        employeeComboBox.getSelectionModel().clearSelection();
        serviceComboBox.getSelectionModel().clearSelection();
        partComboBox.getSelectionModel().clearSelection();
        statusComboBox.getSelectionModel().clearSelection();
        deadlinePicker.setValue(null);
        descriptionArea.clear();
    }

    private void showError(String message) {
        infoLabel.setStyle("-fx-text-fill: red;");
        infoLabel.setText(message);
        FxUtils.clearErrorAfterDelay(infoLabel);
    }

    private void showInfo(String message) {
        infoLabel.setStyle("-fx-text-fill: green;");
        infoLabel.setText(message);
        FxUtils.clearErrorAfterDelay(infoLabel);
    }
}
