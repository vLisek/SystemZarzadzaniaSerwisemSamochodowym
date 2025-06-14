package project.app.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import project.app.dao.ClientTypeDAO;
import project.app.dao.CustomerDAO;
import project.app.model.ClientType;
import project.app.model.Customer;
import project.app.utils.AlertUtils;

import java.sql.SQLException;
import java.util.List;

public class CustomersController {

    private final ClientTypeDAO clientTypeDAO = new ClientTypeDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private ComboBox<ClientType> clientTypeComboBox;
    @FXML
    private TextField companyNameTextField;
    @FXML
    private TableView<Customer> clientTable;
    @FXML
    private TableColumn<Customer, String> firstNameColumn;
    @FXML
    private TableColumn<Customer, String> lastNameColumn;
    @FXML
    private TableColumn<Customer, String> phoneColumn;
    @FXML
    private TableColumn<Customer, String> emailColumn;
    @FXML
    private TableColumn<Customer, String> clientTypeColumn;
    @FXML
    private TableColumn<Customer, String> companyNameColumn;

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
            AlertUtils.showError("Error", "Nie udało się załadować typów klientów: " + e.getMessage());
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
            AlertUtils.showWarning("Niepoprawne dane", "Uzupełnij wszystkie wymagane pola!");
            return;
        }

        if ("Firma".equals(selectedType.getName()) && companyName.isEmpty()) {
            AlertUtils.showWarning("Niepoprawne dane", "Nazwa firmy jest wymagana!");
            return;
        }

        if (!isValidPhone(phone)) {
            AlertUtils.showWarning("Niepoprawne dane", "Niepoprawny numer telefonu! \nWprowadź 9 cyfr lub +48 i 9 cyfr.");
            return;
        }

        if (!email.isEmpty() && !isValidEmail(email)) {
            AlertUtils.showWarning("Niepoprawne dane", "Niepoprawny adres email!");
            return;
        }

        try {
            customerDAO.insertCustomer(firstName, lastName, phone, email, selectedType.getId(), companyName);
            refreshClientTable();
            clearForm();
            AlertUtils.showInfo("Sukces", "Dodano klienta.");
        } catch (SQLException e) {
            AlertUtils.showError("Error", "Błąd dodawania klienta: " + e.getMessage());
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
    }

    @FXML
    public void deleteCustomerFromDatabase() {
        Customer selectedCustomer = clientTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            AlertUtils.showWarning("Błąd", "Musisz wybrać klienta z tabeli!");
            return;
        }

        AlertUtils.showConfirmation("Potwierdzenie usunięcia", "Czy na pewno chcesz usunąć klienta: " + selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName() + "?", () -> {
            try {
                customerDAO.deleteCustomer(selectedCustomer.getCustomerId());
                refreshClientTable();
                AlertUtils.showInfo("Sukces", "Usunięto klienta.");
            } catch (SQLException e) {
                AlertUtils.showWarning("Błąd", "Nie można usunąć klienta – jest powiązany ze zleceniem!");
            }
        });
    }


    private void refreshClientTable() {
        try {
            List<Customer> customerList = customerDAO.getAllCustomers();
            clientTable.setItems(FXCollections.observableArrayList(customerList));
            if (customerList.isEmpty()) {
                AlertUtils.showInfo("Informacja", "Brak klientów w bazie danych.");
            }
        } catch (SQLException e) {
            AlertUtils.showError("Error", "Nie udało się załadować klientów: " + e.getMessage());
        }
    }
}
