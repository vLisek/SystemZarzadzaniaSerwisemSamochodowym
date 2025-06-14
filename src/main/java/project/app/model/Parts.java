package project.app.model;

public class Parts {
    private int partId;
    private String brand;
    private String name;
    private double price;
    private int quantity;

    public Parts(int partId, String brand, String name, double price, int quantity) {
        this.partId = partId;
        this.brand = brand;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Parts(int partId, String name, double unitPrice) {
        this.partId = partId;
        this.name = name;
        this.price = unitPrice;
    }

    public double getUnitPrice() {
        return price;
    }

    public String getDisplayName() {
        return name + " " + brand + " (" + price + ") PLN";
    }

    @Override
    public String toString() {
        return getDisplayName();
    }




    public int getPartId() {
        return partId;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}


