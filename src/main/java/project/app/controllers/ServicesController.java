package project.app.controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import project.app.dao.ServiceDAO;
import project.app.model.Service;
import project.app.utils.AlertUtils;

public class ServicesController {

    private final ObservableList<Service> services = FXCollections.observableArrayList();
    @FXML
    private TableView<Service> serviceTable;
    @FXML
    private TableColumn<Service, String> nameColumn;
    @FXML
    private TableColumn<Service, Double> priceColumn;
    @FXML
    private TableColumn<Service, Integer> durationColumn;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField durationTextField;
    @FXML
    private Button updateButton;
    @FXML
    private Button addButton;

    @FXML
    public void initialize() {
        setupTableColumns();
        serviceTable.setItems(services);
        loadServices();

        serviceTable.getSelectionModel().selectedItemProperty().addListener((_, _, newSel) -> {
            if (newSel != null) {
                fillForm(newSel);
                toggleButtons(true);
            } else {
                clearFields();
                toggleButtons(false);
            }
        });

        toggleButtons(false);
    }

    private void setupTableColumns() {
        nameColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getName()));
        priceColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPrice()));
        durationColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getDuration()));
    }

    private void loadServices() {
        services.clear();
        try {
            services.addAll(ServiceDAO.getAllServices());
        } catch (Exception e) {
            AlertUtils.showError("Błąd", "Nie udało się załadować usług: " + e.getMessage());
        }
    }

    @FXML
    private void addService() {
        Service service = readForm();
        if (service == null) return;

        try {
            ServiceDAO.insertService(service);
            services.add(service);
            clearForm();
        } catch (Exception e) {
            AlertUtils.showError("Błąd", "Nie udało się dodać usługi: " + e.getMessage());
        }
    }

    @FXML
    private void updateService() {
        Service selected = serviceTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            AlertUtils.showWarning("Błąd", "Nie wybrano usługi do edycji.");
            return;
        }

        // Confirm update
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Potwierdzenie aktualizacji");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Czy na pewno chcesz zaktualizować tę usługę?");
        if (confirmAlert.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK) {
            return;
        }

        try {
            String name = nameTextField.getText().trim();
            double price = Double.parseDouble(priceTextField.getText());
            int duration = Integer.parseInt(durationTextField.getText());

            if (name.isEmpty() || price < 0 || duration <= 0) {
                AlertUtils.showWarning("Niepoprawne dane", "Uzupełnij poprawnie wszystkie pola.");
                return;
            }

            selected.setName(name);
            selected.setPrice(price);
            selected.setDuration(duration);

            ServiceDAO.updateService(selected);
            serviceTable.refresh();
            clearFields();

            updateButton.setVisible(false);
            updateButton.setManaged(false);
            addButton.setVisible(true);
            addButton.setManaged(true);
        } catch (NumberFormatException e) {
            AlertUtils.showWarning("Niepoprawne dane", "Cena i czas trwania muszą być liczbami.");
        } catch (Exception e) {
            AlertUtils.showError("Error", "Błąd aktualizacji usługi: " + e.getMessage());
        }
    }

    @FXML
    private void deleteService() {
        Service selected = serviceTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            AlertUtils.showWarning("Błąd", "Nie wybrano usługi do usunięcia.");
            return;
        }

        String serviceInfo = String.format("ID usługi: %d\nNazwa: %s\nCena: %.2f zł", selected.getId(), selected.getName(), selected.getPrice());

        AlertUtils.showConfirmation("Potwierdzenie usunięcia", "Czy na serio chcesz usunąć tę usługę?\n\n" + serviceInfo, () -> {
            try {
                ServiceDAO.deleteService(selected.getId());
                services.remove(selected);
                clearFields();
                updateButton.setVisible(false);
                updateButton.setManaged(false);
                addButton.setVisible(true);
                addButton.setManaged(true);
                AlertUtils.showInfo("Sukces", "Usługa została pomyślnie usunięta.");
            } catch (Exception e) {
                AlertUtils.showWarning("Błąd", "Nie można usunąć usługi – jest powiązana ze zleceniem!");
            }
        });
    }


    @FXML
    private void clearForm() {
        serviceTable.getSelectionModel().clearSelection();
        clearFields();
        toggleButtons(false);
    }

    private Service readForm() {
        String name = nameTextField.getText().trim();
        double price;
        int duration;

        if (name.isEmpty()) {
            AlertUtils.showWarning("Niepoprawne dane", "Nazwa usługi nie może być pusta.");
            return null;
        }

        try {
            price = Double.parseDouble(priceTextField.getText());
            duration = Integer.parseInt(durationTextField.getText());
        } catch (NumberFormatException e) {
            AlertUtils.showWarning("Niepoprawne dane", "Cena i czas trwania muszą być liczbami.");
            return null;
        }

        if (price < 0 || duration <= 0) {
            AlertUtils.showWarning("Niepoprawne dane", "Cena musi być nieujemna, a czas trwania większy od 0.");
            return null;
        }

        return new Service(name, price, duration);
    }

    private void fillForm(Service service) {
        nameTextField.setText(service.getName());
        priceTextField.setText(String.valueOf(service.getPrice()));
        durationTextField.setText(String.valueOf(service.getDuration()));
    }

    private void clearFields() {
        nameTextField.clear();
        priceTextField.clear();
        durationTextField.clear();
    }

    private void toggleButtons(boolean isEditing) {
        updateButton.setVisible(isEditing);
        updateButton.setManaged(isEditing);

        addButton.setVisible(!isEditing);
        addButton.setManaged(!isEditing);
    }
}
