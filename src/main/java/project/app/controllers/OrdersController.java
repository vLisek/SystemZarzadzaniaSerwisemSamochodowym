package project.app.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import project.app.dao.*;
import project.app.model.*;
import project.app.utils.AlertUtils;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class OrdersController {

    private final OrderDAO orderDAO = new OrderDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final CarDAO vehicleDAO = new CarDAO();
    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    private final PartsDAO partsDAO = new PartsDAO();
    @FXML
    private TableView<Order> ordersTable;
    @FXML
    private TableColumn<Order, String> clientColumn;
    @FXML
    private TableColumn<Order, String> vehicleColumn;
    @FXML
    private TableColumn<Order, String> employeeColumn;
    @FXML
    private TableColumn<Order, LocalDate> deadlineColumn;
    @FXML
    private TableColumn<Order, String> statusColumn;
    @FXML
    private TableColumn<Order, Double> totalCostColumn;
    @FXML
    private ComboBox<Customer> clientComboBox;
    @FXML
    private ComboBox<VehicleDisplay> vehicleComboBox;
    @FXML
    private ComboBox<Employee> employeeComboBox;
    @FXML
    private ComboBox<Service> serviceComboBox;
    @FXML
    private ComboBox<Parts> partComboBox;
    @FXML
    private ComboBox<String> statusComboBox;
    @FXML
    private DatePicker deadlinePicker;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private Button changeStatusButton;
    @FXML
    private ComboBox<String> statusChangeComboBox;

    public void initialize() {
        setupTableColumns();
        loadAllComboBoxData();
        refreshOrdersTable();

        statusChangeComboBox.setItems(FXCollections.observableArrayList("Nowe", "W trakcie", "Zakończone"));
        changeStatusButton.setOnAction(_ -> changeOrderStatus());
    }

    private void setupTableColumns() {
        clientColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCustomer().getFullName()));
        vehicleColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVehicle().toString()));
        employeeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmployee().getFullName()));
        deadlineColumn.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        totalCostColumn.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
    }

    private void changeOrderStatus() {
        Order selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
        String newStatus = statusChangeComboBox.getValue();

        if (selectedOrder == null) {
            AlertUtils.showWarning("Brak wyboru", "Wybierz zlecenie do zmiany statusu.");
            return;
        }
        if (newStatus == null || newStatus.isBlank()) {
            AlertUtils.showWarning("Brak statusu", "Wybierz nowy status.");
            return;
        }
        if (selectedOrder.getStatus().equals(newStatus)) {
            AlertUtils.showInfo("Status niezmieniony", "Status jest już ustawiony na: " + newStatus);
            return;
        }

        try {
            orderDAO.updateOrderStatus(selectedOrder.getOrderId(), newStatus);
            refreshOrdersTable();
            AlertUtils.showInfo("Sukces", "Status zlecenia został zmieniony.");
        } catch (SQLException ex) {
            AlertUtils.showError("Błąd", "Nie udało się zmienić statusu: " + ex.getMessage());
        }
    }

    private void loadAllComboBoxData() {
        try {
            clientComboBox.setItems(FXCollections.observableArrayList(customerDAO.getAllCustomers()));
            vehicleComboBox.setItems(FXCollections.observableArrayList(vehicleDAO.getAllVehiclesForDisplay()));
            employeeComboBox.setItems(FXCollections.observableArrayList(employeeDAO.getAllEmployees()));
            serviceComboBox.setItems(FXCollections.observableArrayList(ServiceDAO.getAllServices()));
            partComboBox.setItems(FXCollections.observableArrayList(partsDAO.getAllParts()));
            statusComboBox.setItems(FXCollections.observableArrayList("Nowe", "W trakcie", "Zakończone"));
        } catch (SQLException e) {
            AlertUtils.showError("Error", "Błąd ładowania danych: " + e.getMessage());

        }
    }

    private void refreshOrdersTable() {
        try {
            List<Order> orders = orderDAO.getAllOrders();
            ordersTable.setItems(FXCollections.observableArrayList(orders));
        } catch (Exception e) {
            AlertUtils.showError("Error", "Błąd ładowania zleceń: " + e.getMessage());
        }
    }

    @FXML
    private void addOrder() {
        try {
            Order newOrder = createOrderFromForm();
            if (newOrder == null) return;

            if (orderDAO.vehicleHasActiveOrder(newOrder.getVehicle().getVehicleId())) {
                AlertUtils.showWarning("Błąd", "Wybrany pojazd ma już aktywne zlecenie.");
                return;
            }

            if (orderDAO.employeeHasActiveOrder(newOrder.getEmployee().getEmployeeId())) {
                AlertUtils.showWarning("Błąd", "Pracownik prowadzi już inne zlecenie.");
                return;
            }

            orderDAO.addOrder(newOrder);
            refreshOrdersTable();
            clearForm();
            AlertUtils.showInfo("Sukces", "Zlecenie dodane pomyślnie.");
        } catch (SQLException e) {
            AlertUtils.showError("Error", "Błąd zapisu do bazy: " + e.getMessage());
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

        if (customer == null || vehicle == null || employee == null || service == null || part == null || deadline == null || status == null || description == null || description.isBlank()) {
            AlertUtils.showWarning("Niepoprawne dane", "Wszystkie pola są wymagane.");
            return null;
        }

        double totalCost = service.getPrice() + part.getUnitPrice();

        return new Order(0, customer, vehicle, employee, service, part, deadline, status, description, totalCost);
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

    @FXML
    private void clearFormButtonPressed() {
        clearForm();
    }

    @FXML
    private void deleteOrder() {
        Order selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            AlertUtils.showWarning("Brak wyboru", "Proszę wybrać zlecenie do usunięcia.");
            return;
        }

        String orderInfo = String.format("Zlecenie ID: %d\nKlient: %s\nPracownik: %s", selectedOrder.getOrderId(), selectedOrder.getCustomer(), selectedOrder.getEmployee());

        AlertUtils.showConfirmation("Potwierdzenie usunięcia", "Czy na pewno chcesz usunąć następujące zlecenie?\n\n" + orderInfo, () -> {
            try {
                orderDAO.deleteOrder(selectedOrder.getOrderId());
                refreshOrdersTable();
                AlertUtils.showInfo("Sukces", "Zlecenie zostało usunięte.");
            } catch (SQLException e) {
                AlertUtils.showError("Błąd", "Nie udało się usunąć zlecenia: " + e.getMessage());
            }
        });
    }


}
