package project.app.controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import project.app.dao.ServiceDAO;
import project.app.model.Service;
import project.app.utils.FxUtils;

public class ServicesController {

    @FXML private TableView<Service> serviceTable;
    @FXML private TableColumn<Service, String> nameColumn;
    @FXML private TableColumn<Service, Double> priceColumn;
    @FXML private TableColumn<Service, Integer> durationColumn;

    @FXML private Label errorMessage;

    @FXML private TextField nameTextField;
    @FXML private TextField priceTextField;
    @FXML private TextField durationTextField;

    @FXML private Button updateButton;
    @FXML private Button addButton;

    private final ObservableList<Service> services = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getName()));
        priceColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPrice()));
        durationColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getDuration()));

        serviceTable.setItems(services);
        loadServices();

        serviceTable.setOnMouseClicked(this::handleRowClick);

        updateButton.setVisible(false);
        updateButton.setManaged(false);

        addButton.setVisible(true);
        addButton.setManaged(true);

        serviceTable.setOnMouseClicked(_ -> {
            Service selected = serviceTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                nameTextField.setText(selected.getName());
                priceTextField.setText(String.valueOf(selected.getPrice()));
                durationTextField.setText(String.valueOf(selected.getDuration()));

                updateButton.setVisible(true);
                updateButton.setManaged(true);

                addButton.setVisible(false);
                addButton.setManaged(false);
            }
        });
    }

    private void loadServices() {
        services.clear();
        try {
            services.addAll(ServiceDAO.getAllServices());
        } catch (Exception e) {
            showError("Błąd ładowania usług: " + e.getMessage());
        }
    }

    @FXML
    private void addService() {
        try {
            String name = nameTextField.getText().trim();
            double price = Double.parseDouble(priceTextField.getText());
            int duration = Integer.parseInt(durationTextField.getText());

            if (name.isEmpty() || price < 0 || duration <= 0) {
                showError("Uzupełnij poprawnie wszystkie pola.");
                return;
            }

            Service service = new Service(name, price, duration);
            ServiceDAO.insertService(service);
            services.add(service);
            clearFields();

        } catch (NumberFormatException e) {
            showError("Cena i czas trwania muszą być liczbami.");
        } catch (Exception e) {
            showError("Błąd dodawania usługi: " + e.getMessage());
        }
    }

    @FXML
    private void clearForm() {
        clearFields();
        updateButton.setVisible(false);
        updateButton.setManaged(false);
        addButton.setVisible(true);
        addButton.setManaged(true);
    }

    @FXML
    private void updateService() {
        Service selected = serviceTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Nie wybrano usługi do edycji.");
            return;
        }

        try {
            String name = nameTextField.getText().trim();
            double price = Double.parseDouble(priceTextField.getText());
            int duration = Integer.parseInt(durationTextField.getText());

            if (name.isEmpty() || price < 0 || duration <= 0) {
                showError("Uzupełnij poprawnie wszystkie pola.");
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
            showError("Cena i czas trwania muszą być liczbami.");
        } catch (Exception e) {
            showError("Błąd aktualizacji usługi: " + e.getMessage());
        }
    }

    @FXML
    private void deleteService() {
        Service selected = serviceTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Nie wybrano usługi do usunięcia.");
            return;
        }

        try {
            ServiceDAO.deleteService(selected.getId());
            services.remove(selected);
            clearFields();
            updateButton.setVisible(false);
            updateButton.setManaged(false);
            addButton.setVisible(true);
            addButton.setManaged(true);
        } catch (Exception e) {
            showError("Błąd usuwania usługi: " + e.getMessage());
        }
    }

    private void handleRowClick(MouseEvent event) {
        Service selected = serviceTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            nameTextField.setText(selected.getName());
            priceTextField.setText(String.valueOf(selected.getPrice()));
            durationTextField.setText(String.valueOf(selected.getDuration()));
        }
    }

    private void clearFields() {
        nameTextField.clear();
        priceTextField.clear();
        durationTextField.clear();
        serviceTable.getSelectionModel().clearSelection();
    }

    private void showError(String message) {
        errorMessage.setText(message);
        FxUtils.clearErrorAfterDelay(errorMessage);
    }
}

