package project.app.model;

public class VehicleDisplay {
    private final int vehicleId;
    private final String brand;
    private final String model;
    private final int productionYear;
    private String bodyType;
    private String fuelType;
    private String engineType;
    private String driveType;
    private int engineCapacity;
    private int mileage;

    public VehicleDisplay(int vehicleId, String brand, String model, String bodyType, String fuelType, String engineType, String driveType, int engineCapacity, int productionYear, int mileage) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.bodyType = bodyType;
        this.fuelType = fuelType;
        this.engineType = engineType;
        this.driveType = driveType;
        this.engineCapacity = engineCapacity;
        this.productionYear = productionYear;
        this.mileage = mileage;
    }

    public VehicleDisplay(int id, String brand, String model, int productionYear) {
        this.vehicleId = id;
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
    }

    public String getDisplayName() {
        return brand + " " + model + " - " + productionYear;
    }

    @Override
    public String toString() {
        return getDisplayName();
    }


    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getBodyType() {
        return bodyType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getEngineType() {
        return engineType;
    }

    public String getDriveType() {
        return driveType;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public int getMileage() {
        return mileage;
    }

    public int getVehicleId() {
        return vehicleId;
    }
}

