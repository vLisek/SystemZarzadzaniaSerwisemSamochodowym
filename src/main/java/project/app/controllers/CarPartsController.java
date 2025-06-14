package project.app.controllers;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import project.app.dao.PartsDAO;
import project.app.model.Parts;
import project.app.utils.FxUtils;

public class CarPartsController {

    @FXML private TextField brandField;
    @FXML private TextField nameField;
    @FXML private TextField priceField;
    @FXML private TextField quantityField;

    @FXML private Label infoLabel;

    @FXML private TableView<Parts> partsTable;
    @FXML private TableColumn<Parts, String> brandColumn;
    @FXML private TableColumn<Parts, String> nameColumn;
    @FXML private TableColumn<Parts, Double> priceColumn;
    @FXML private TableColumn<Parts, Integer> quantityColumn;

    private final PartsDAO partsDAO = new PartsDAO();

    @FXML
    public void initialize() {
        brandColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getBrand()));
        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        priceColumn.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getPrice()).asObject());
        quantityColumn.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getQuantity()).asObject());

        loadParts();
    }

    @FXML
    private void onAddPart() {
        String brand = brandField.getText().trim();
        String name = nameField.getText().trim();
        String priceText = priceField.getText().trim();
        String quantityText = quantityField.getText().trim();

        if (brand.isEmpty() || name.isEmpty() || priceText.isEmpty() || quantityText.isEmpty()) {
            setInfo("Wszystkie pola muszą być wypełnione.");
            return;
        }

        double price;
        int quantity;

        try {
            price = Double.parseDouble(priceText);
            if (price < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            setInfo("Cena musi być poprawną liczbą dodatnią.");
            return;
        }

        try {
            quantity = Integer.parseInt(quantityText);
            if (quantity < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            setInfo("Ilość musi być poprawną liczbą całkowitą dodatnią.");
            return;
        }

        Parts part = new Parts(0, brand, name, price, quantity);
        partsDAO.addPart(part);
        loadParts();
        clearForm();
        setInfo("Dodano część.");
    }


    @FXML
    private void onDeletePart() {
        Parts selectedPart = partsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            setInfo("Wybierz część do usunięcia.");
            return;
        }

        partsDAO.deletePart(selectedPart.getPartId());
        loadParts();
        clearForm();
        setInfo("Usunięto część.");
    }

    @FXML
    private void onClearForm() {
        clearForm();
    }

    private void loadParts() {
        partsTable.setItems(FXCollections.observableArrayList(partsDAO.getAllParts()));
    }

    private void clearForm() {
        brandField.clear();
        nameField.clear();
        priceField.clear();
        quantityField.clear();
    }

    private void setInfo(String message) {
        infoLabel.setText(message);
        FxUtils.clearErrorAfterDelay(infoLabel);
    }
}
