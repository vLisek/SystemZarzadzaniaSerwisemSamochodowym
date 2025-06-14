package project.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import project.app.dao.*;
import project.app.model.*;
import project.app.utils.AlertUtils;

import java.sql.SQLException;
import java.util.List;

public class CarsController {

    private final ModelDAO modelDAO = new ModelDAO();
    private final BodyTypeDAO bodyTypeDAO = new BodyTypeDAO();
    private final FuelTypeDAO fuelTypeDAO = new FuelTypeDAO();
    private final EngineTypeDAO engineTypeDAO = new EngineTypeDAO();
    private final DriveTypeDAO driveTypeDAO = new DriveTypeDAO();

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
    private TextField engineCapacityTextField;
    @FXML
    private TextField productionYearTextField;
    @FXML
    private TextField mileageTextField;
    @FXML
    private TableView<VehicleDisplay> vehicleTable;
    @FXML
    private TableColumn<VehicleDisplay, String> bodyTypeColumn;
    @FXML
    private TableColumn<VehicleDisplay, String> brandColumn;
    @FXML
    private TableColumn<VehicleDisplay, String> modelColumn;
    @FXML
    private TableColumn<VehicleDisplay, String> fuelTypeColumn;
    @FXML
    private TableColumn<VehicleDisplay, String> engineTypeColumn;
    @FXML
    private TableColumn<VehicleDisplay, String> engineCapacityColumn;
    @FXML
    private TableColumn<VehicleDisplay, String> driveTypeColumn;
    @FXML
    private TableColumn<VehicleDisplay, String> productionYearColumn;
    @FXML
    private TableColumn<VehicleDisplay, String> mileageColumn;

    @FXML
    public void initialize() {
        loadInitialData();
        setupListeners();

        bodyTypeColumn.setCellValueFactory(new PropertyValueFactory<>("bodyType"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        fuelTypeColumn.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
        engineTypeColumn.setCellValueFactory(new PropertyValueFactory<>("engineType"));
        engineCapacityColumn.setCellValueFactory(new PropertyValueFactory<>("engineCapacity"));
        driveTypeColumn.setCellValueFactory(new PropertyValueFactory<>("driveType"));
        productionYearColumn.setCellValueFactory(new PropertyValueFactory<>("productionYear"));
        mileageColumn.setCellValueFactory(new PropertyValueFactory<>("mileage"));

        refreshVehicleTable();
    }

    @FXML
    public void clearForm() {
        brandComboBox.getSelectionModel().select(0);
        modelComboBox.getSelectionModel().select(0);
        bodyTypeComboBox.getSelectionModel().select(0);
        fuelTypeComboBox.getSelectionModel().select(0);
        engineTypeComboBox.getSelectionModel().select(0);
        driveTypeComboBox.getSelectionModel().select(0);

        engineCapacityTextField.clear();
        productionYearTextField.clear();
        mileageTextField.clear();
    }

    @FXML
    public void deleteVehicle() {
        VehicleDisplay selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();

        if (selectedVehicle == null) {
            AlertUtils.showWarning("Błąd", "Musisz wybrać samochód z tabeli!");
            return;
        }

        String vehicleInfo = String.format("ID pojazdu: %d\nMarka: %s\nModel: %s", selectedVehicle.getVehicleId(), selectedVehicle.getBrand(), selectedVehicle.getModel());

        AlertUtils.showConfirmation("Potwierdzenie usunięcia", "Czy na pewno chcesz usunąć ten pojazd?\n\n" + vehicleInfo, () -> {
            try {
                CarDAO carDAO = new CarDAO();
                carDAO.deleteVehicle(selectedVehicle.getVehicleId());
                vehicleTable.getItems().remove(selectedVehicle);
                refreshVehicleTable();
                AlertUtils.showInfo("Sukces", "Usunięto samochód");
            } catch (SQLException e) {
                AlertUtils.showWarning("Błąd", "Nie można usunąć pojazdu – jest powiązany ze zleceniem!");
            }
        });
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

            if (brand == null || brand.getId() == 0 || model == null || model.getId() == 0 || bodyType == null || bodyType.getId() == 0 || fuelType == null || fuelType.getId() == 0 || engineType == null || engineType.getId() == 0 || driveType == null || driveType.getId() == 0) {
                AlertUtils.showWarning("Niepoprawne dane", "Uzupełnij wszystkie pola wyboru!");
                return;
            }

            if (engineCapacityTextField.getText().trim().isEmpty() || productionYearTextField.getText().trim().isEmpty() || mileageTextField.getText().trim().isEmpty()) {
                AlertUtils.showWarning("Niepoprawne dane", "Uzupełnij wszystkie pola tekstowe!");
                return;
            }

            int engineCapacity = Integer.parseInt(engineCapacityTextField.getText().trim());
            int productionYear = Integer.parseInt(productionYearTextField.getText().trim());
            int mileage = Integer.parseInt(mileageTextField.getText().trim());

            if (productionYear < 1900 || productionYear > 2025) {
                AlertUtils.showWarning("Niepoprawne dane", "Rok produkcji musi być liczbą z zakresu 1900–2025.");
                return;
            }

            int vehicleId = CarDAO.insertVehicle(bodyType.getId(), brand.getId(), model.getId(), fuelType.getId(), engineType.getId(), engineCapacity, driveType.getId(), productionYear, mileage);

            VehicleDisplay newVehicle = new VehicleDisplay(vehicleId, brand.getName(), model.getName(), bodyType.getName(), fuelType.getName(), engineType.getName(), driveType.getName(), engineCapacity, productionYear, mileage);

            vehicleTable.getItems().add(newVehicle);

            clearForm();
            AlertUtils.showInfo("Sukces", "Dodano pojazd.");
        } catch (NumberFormatException e) {
            AlertUtils.showWarning("Niepoprawne dane", "Niepoprawna liczba w polach tekstowych.");
        } catch (SQLException e) {
            AlertUtils.showError("Error", "Błąd zapisu do bazy.");
        }
    }

    public void loadBodyTypes() {
        try {
            List<BodyType> bodyTypes = bodyTypeDAO.getAllBodyTypes();
            BodyType placeholder = new BodyType(0, "Wybierz typ nadwozia");
            bodyTypeComboBox.getItems().clear();
            bodyTypeComboBox.getItems().add(placeholder);
            bodyTypeComboBox.getItems().addAll(bodyTypes);
            bodyTypeComboBox.getSelectionModel().select(0);
        } catch (SQLException e) {
            AlertUtils.showError("Error", "Nie udało się załadować typów nadwozia.");
        }
    }

    public void loadBrands() {
        try {
            List<Brand> brands = BrandDAO.getAllBrands();
            Brand placeholder = new Brand(0, "Wybierz markę");
            brandComboBox.getItems().clear();
            brandComboBox.getItems().add(placeholder);
            brandComboBox.getItems().addAll(brands);
            brandComboBox.getSelectionModel().select(0);
        } catch (SQLException e) {
            AlertUtils.showError("Error", "Nie udało się załadować marek.");
        }
    }

    public void loadModelsForBrand(Brand brand) {
        try {
            List<Model> models = modelDAO.getModelsByBrand(brand);
            Model placeholder = new Model(0, "Wybierz model", brand);
            modelComboBox.getItems().clear();
            modelComboBox.getItems().add(placeholder);
            modelComboBox.getItems().addAll(models);
            modelComboBox.getSelectionModel().select(placeholder);
        } catch (SQLException e) {
            AlertUtils.showError("Error", "Nie udało się załadować modeli.");
        }
    }

    public void loadFuelTypes() {
        try {
            List<FuelType> fuelTypes = fuelTypeDAO.getAllFuelTypes();
            FuelType placeholder = new FuelType(0, "Wybierz rodzaj paliwa");
            fuelTypeComboBox.getItems().clear();
            fuelTypeComboBox.getItems().add(placeholder);
            fuelTypeComboBox.getItems().addAll(fuelTypes);
            fuelTypeComboBox.getSelectionModel().select(0);
        } catch (SQLException e) {
            AlertUtils.showError("Error", "Nie udało się załadować rodzajów paliwa.");
        }
    }

    public void loadEngineTypes() {
        try {
            List<EngineType> engineTypes = engineTypeDAO.getAllEngineTypes();
            EngineType placeholder = new EngineType(0, "Wybierz rodzaj silnika");
            engineTypeComboBox.getItems().clear();
            engineTypeComboBox.getItems().add(placeholder);
            engineTypeComboBox.getItems().addAll(engineTypes);
            engineTypeComboBox.getSelectionModel().select(0);
        } catch (SQLException e) {
            AlertUtils.showError("Error", "Nie udało się załadować silników.");
        }
    }

    public void loadDriveTypes() {
        try {
            List<DriveType> driveTypes = driveTypeDAO.getAllDriveTypes();
            DriveType placeholder = new DriveType(0, "Wybierz rodzaj napędu");
            driveTypeComboBox.getItems().clear();
            driveTypeComboBox.getItems().add(placeholder);
            driveTypeComboBox.getItems().addAll(driveTypes);
            driveTypeComboBox.getSelectionModel().select(0);
        } catch (SQLException e) {
            AlertUtils.showError("Error", "Nie udało się załadować typów napędu.");
        }
    }

    private void refreshVehicleTable() {
        try {
            CarDAO carDAO = new CarDAO();
            List<VehicleDisplay> vehicles = carDAO.getAllVehiclesForDisplay();
            vehicleTable.getItems().setAll(vehicles);
        } catch (SQLException e) {
            AlertUtils.showError("Error", "Nie udało się załadować pojazdów.");
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
}
