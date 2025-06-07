package project.app.model;

@SuppressWarnings("unused")
public class VehicleDisplay {
    private final int vehicleId;
    private final String brand;
    private final String model;
    private final String bodyType;
    private final String fuelType;
    private final String engineType;
    private final String driveType;
    private final int engineCapacity;
    private final int productionYear;
    private final int mileage;

    public VehicleDisplay(
            int vehicleId,
            String brand,
            String model,
            String bodyType,
            String fuelType,
            String engineType,
            String driveType,
            int engineCapacity,
            int productionYear,
            int mileage
    ) {
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
        return mileage; }

    public int getVehicleId() {
        return vehicleId;
    }
}

