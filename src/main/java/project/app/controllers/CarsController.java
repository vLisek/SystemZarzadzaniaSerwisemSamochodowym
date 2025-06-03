package project.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import project.app.dao.*;
import project.app.model.*;
import project.app.utils.FxUtils;

import java.sql.SQLException;
import java.util.List;

public class CarsController {

    @FXML
    private ComboBox<Brand> brandComboBox;

    @FXML
    private ComboBox<Model> modelComboBox;

    @FXML
    private ComboBox<BodyType> bodyTypeComboBox;

    @FXML
    private ComboBox<FuelType> fuelTypeComboBox;

    @FXML
    private ComboBox<EngineType> engineTypeComboBox;

    @FXML
    private ComboBox<DriveType> driveTypeComboBox;


    @FXML
    private Label errorMessage;

    @FXML
    private TextField engineCapacityTextField;

    @FXML
    private TextField productionYearTextField;

    @FXML
    private TextField mileageTextField;

    private final ModelDAO modelDAO = new ModelDAO();
    private final BodyTypeDAO bodyTypeDAO = new BodyTypeDAO();
    private final FuelTypeDAO fuelTypeDAO = new FuelTypeDAO();
    private final EngineTypeDAO engineTypeDAO = new EngineTypeDAO();
    private final DriveTypeDAO driveTypeDAO = new DriveTypeDAO();


    @FXML
    public void initialize() {
        loadInitialData();
        setupListeners();
    }

    @FXML
    public void clearForm() {
        // Czyścimy wszystkie ComboBoxy
        brandComboBox.getSelectionModel().clearSelection();
        brandComboBox.setValue(null);

        modelComboBox.getItems().clear();
        modelComboBox.setValue(null);

        bodyTypeComboBox.getSelectionModel().clearSelection();
        bodyTypeComboBox.setValue(null);

        fuelTypeComboBox.getSelectionModel().clearSelection();
        fuelTypeComboBox.setValue(null);

        engineTypeComboBox.getSelectionModel().clearSelection();
        engineTypeComboBox.setValue(null);

        driveTypeComboBox.getSelectionModel().clearSelection();
        driveTypeComboBox.setValue(null);

        engineCapacityTextField.clear();
        productionYearTextField.clear();
        mileageTextField.clear();
    }

    @FXML
    public void addCarToDatabase() {
        try {
            Brand brand = brandComboBox.getValue();
            Model model = modelComboBox.getValue();
            BodyType bodyType = bodyTypeComboBox.getValue();
            FuelType fuelType = fuelTypeComboBox.getValue();
            EngineType engineType = engineTypeComboBox.getValue();
            DriveType driveType = driveTypeComboBox.getValue();

            int engineCapacity = Integer.parseInt(engineCapacityTextField.getText().trim());
            int productionYear = Integer.parseInt(productionYearTextField.getText().trim());
            int mileage = Integer.parseInt(mileageTextField.getText().trim());

            if (brand == null || model == null || bodyType == null || fuelType == null ||
                    engineType == null || driveType == null) {
                showError("Uzupełnij wszystkie pola!");
                return;
            }

            CarDAO carDAO = new CarDAO();
            carDAO.insertVehicle(
                    bodyType.getId(),
                    brand.getId(),
                    model.getId(),
                    fuelType.getId(),
                    engineType.getId(),
                    engineCapacity,
                    driveType.getId(),
                    productionYear,
                    mileage
            );

            clearForm();
            errorMessage.setText("Dodano pojazd.");
        } catch (NumberFormatException e) {
            showError("Niepoprawna liczba w polach tekstowych.");
        } catch (SQLException e) {
            showError("Błąd zapisu do bazy.");
        }
    }

    private void loadInitialData() {
        loadBrands();
        loadBodyTypes();
        loadFuelTypes();
        loadEngineTypes();
        loadDriveTypes();
    }

    private void setupListeners() {
        brandComboBox.setOnAction(_ -> {
            Brand selectedBrand = brandComboBox.getValue();
            if (selectedBrand != null) {
                loadModelsForBrand(selectedBrand);
            } else {
                modelComboBox.getItems().clear();
            }
            modelComboBox.setValue(null);
        });
    }

    private void showError(String message) {
        errorMessage.setText(message);
        FxUtils.clearErrorAfterDelay(errorMessage);
    }

    public void loadBrands() {
        try {
            List<Brand> brands = BrandDAO.getAllBrands();
            brandComboBox.getItems().setAll(brands);
        } catch (SQLException e) {
            showError("Nie udało się załadować marek.");
        }
    }

    public void loadModelsForBrand(Brand brand) {
        try {
            List<Model> models = modelDAO.getModelsByBrand(brand);
            modelComboBox.getItems().setAll(models);
            modelComboBox.setValue(null); // nie wybieraj nic domyślnie
        } catch (SQLException e) {
            showError("Nie udało się załadować modeli.");
        }
    }


    public void loadBodyTypes() {
        try {
            List<BodyType> bodyTypes = bodyTypeDAO.getAllBodyTypes();
            bodyTypeComboBox.getItems().setAll(bodyTypes);
        } catch (SQLException e) {
            showError("Nie udało się załadować typów nadwozia.");
        }
    }

    public void loadFuelTypes() {
        try {
            List<FuelType> fuelTypes = fuelTypeDAO.getAllFuelTypes();
            fuelTypeComboBox.getItems().setAll(fuelTypes);
        } catch (SQLException e) {
            showError("Nie udało się załadować rodzajów paliwa.");
        }
    }

    public void loadEngineTypes() {
        try {
            List<EngineType> engineTypes = engineTypeDAO.getAllEngineTypes();
            engineTypeComboBox.getItems().setAll(engineTypes);
        } catch (SQLException e) {
            showError("Nie udało się załadować silników.");
        }
    }

    public void loadDriveTypes() {
        try {
            List<DriveType> driveTypes = driveTypeDAO.getAllDriveTypes();
            driveTypeComboBox.getItems().setAll(driveTypes);
        } catch (SQLException e) {
            showError("Nie udało się załadować typów napędu.");
        }
    }
}
