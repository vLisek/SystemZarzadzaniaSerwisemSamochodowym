package project.app.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import project.app.dao.ClientTypeDAO;
import project.app.dao.CustomerDAO;
import project.app.model.ClientType;
import project.app.model.Customer;
import project.app.utils.FxUtils;

import java.sql.SQLException;
import java.util.List;

public class CustomersController {

    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField phoneTextField;
    @FXML private TextField emailTextField;
    @FXML private ComboBox<ClientType> clientTypeComboBox;
    @FXML private TextField companyNameTextField;

    @FXML private Label errorMessage;

    @FXML private TableView<Customer> clientTable;
    @FXML private TableColumn<Customer, String> firstNameColumn;
    @FXML private TableColumn<Customer, String> lastNameColumn;
    @FXML private TableColumn<Customer, String> phoneColumn;
    @FXML private TableColumn<Customer, String> emailColumn;
    @FXML private TableColumn<Customer, String> clientTypeColumn;
    @FXML private TableColumn<Customer, String> companyNameColumn;

    private final ClientTypeDAO clientTypeDAO = new ClientTypeDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();

    @FXML
    public void initialize() {
        companyNameTextField.setDisable(true);

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        clientTypeColumn.setCellValueFactory(new PropertyValueFactory<>("clientType"));
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));

        try {
            List<ClientType> clientTypes = clientTypeDAO.getAllClientTypes();
            clientTypeComboBox.setItems(FXCollections.observableArrayList(clientTypes));
        } catch (SQLException e) {
            showError("Nie udało się załadować typów klientów: " + e.getMessage());
        }

        clientTypeComboBox.setOnAction(_ -> {
            ClientType selectedType = clientTypeComboBox.getValue();
            if (selectedType != null && "Firma".equals(selectedType.getName())) {
                companyNameTextField.setDisable(false);
            } else {
                companyNameTextField.setDisable(true);
                companyNameTextField.clear();
            }
        });

        refreshClientTable();
    }

    @FXML
    public void addCustomerToDatabase() {
        String firstName = firstNameTextField.getText().trim();
        String lastName = lastNameTextField.getText().trim();
        String phone = phoneTextField.getText().trim();
        String email = emailTextField.getText().trim();
        ClientType selectedType = clientTypeComboBox.getValue();
        String companyName = companyNameTextField.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty() || selectedType == null) {
            showError("Uzupełnij wszystkie wymagane pola!");
            return;
        }

        if ("Firma".equals(selectedType.getName()) && companyName.isEmpty()) {
            showError("Nazwa firmy jest wymagana!");
            return;
        }

        if (!isValidPhone(phone)) {
            showError("Niepoprawny numer telefonu! \nWprowadź 9 cyfr lub +48 i 9 cyfr.");
            return;
        }

        if (!email.isEmpty() && !isValidEmail(email)) {
            showError("Niepoprawny adres email!");
            return;
        }

        try {
            customerDAO.insertCustomer(firstName, lastName, phone, email, selectedType.getId(), companyName);
            refreshClientTable();
            clearForm();
            showError("Dodano klienta.");
        } catch (SQLException e) {
            showError("Błąd dodawania klienta: " + e.getMessage());
        }
    }

    private boolean isValidPhone(String phone) {
        return phone.matches("(\\+48)?\\d{9}");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
        return email.matches(emailRegex);
    }


    @FXML
    public void clearForm() {
        firstNameTextField.clear();
        lastNameTextField.clear();
        phoneTextField.clear();
        emailTextField.clear();
        clientTypeComboBox.getSelectionModel().clearSelection();
        companyNameTextField.clear();
        companyNameTextField.setDisable(true);
        errorMessage.setText("");
    }

    @FXML
    public void deleteCustomerFromDatabase() {
        Customer selectedCustomer = clientTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            showError("Musisz wybrać klienta z tabeli!");
            return;
        }

        try {
            customerDAO.deleteCustomer(selectedCustomer.getCustomerId());
            refreshClientTable();
            showError("Usunięto klienta.");
        } catch (SQLException e) {
            showError("Błąd usuwania klienta: " + e.getMessage());
        }
    }

    private void refreshClientTable() {
        try {
            List<Customer> customerList = customerDAO.getAllCustomers();
            clientTable.setItems(FXCollections.observableArrayList(customerList));
            if (customerList.isEmpty()) {
                showError("Brak klientów w bazie danych.");
            }
        } catch (SQLException e) {
            showError("Nie udało się załadować klientów: " + e.getMessage());
        }
    }

    private void showError(String message) {
        errorMessage.setText(message);
        FxUtils.clearErrorAfterDelay(errorMessage);
    }
}
