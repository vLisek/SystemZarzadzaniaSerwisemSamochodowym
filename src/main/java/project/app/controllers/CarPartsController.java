package project.app.controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import project.app.dao.PartsDAO;
import project.app.model.Parts;
import project.app.utils.AlertUtils;

public class CarPartsController {

    private final PartsDAO partsDAO = new PartsDAO();
    @FXML
    private TextField brandField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField quantityField;
    @FXML
    private TableView<Parts> partsTable;
    @FXML
    private TableColumn<Parts, String> brandColumn;
    @FXML
    private TableColumn<Parts, String> nameColumn;
    @FXML
    private TableColumn<Parts, Double> priceColumn;
    @FXML
    private TableColumn<Parts, Integer> quantityColumn;

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
            AlertUtils.showWarning("Niepoprawne dane", "Wszystkie pola muszą być wypełnione.");
            return;
        }

        double price;
        int quantity;

        try {
            price = Double.parseDouble(priceText);
            if (price < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            AlertUtils.showWarning("Niepoprawne dane", "Cena musi być poprawną liczbą dodatnią.");
            return;
        }

        try {
            quantity = Integer.parseInt(quantityText);
            if (quantity < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            AlertUtils.showWarning("Niepoprawne dane", "Ilość musi być poprawną liczbą całkowitą dodatnią.");
            return;
        }

        Parts part = new Parts(0, brand, name, price, quantity);
        partsDAO.addPart(part);
        loadParts();
        clearForm();
        AlertUtils.showInfo("Sukces", "Dodano część");
    }


    @FXML
    private void onDeletePart() {
        Parts selectedPart = partsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            AlertUtils.showWarning("Błąd", "Wybierz część do usunięcia.");
            return;
        }

        String partInfo = String.format("ID części: %d\nNazwa: %s\nProducent: %s\nCena: %.2f zł", selectedPart.getPartId(), selectedPart.getName(), selectedPart.getBrand(), selectedPart.getPrice());

        AlertUtils.showConfirmation("Potwierdzenie usunięcia", "Czy na pewno chcesz usunąć tę część?\n\n" + partInfo, () -> {
            try {
                partsDAO.deletePart(selectedPart.getPartId());
                loadParts();
                clearForm();
                AlertUtils.showInfo("Sukces", "Usunięto część");
            } catch (Exception e) {
                AlertUtils.showWarning("Błąd", "Nie można usunąć części – jest powiązana ze zleceniem!");
            }
        });
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
}
