package project.app.model;

public class Model {
    private final int id;
    private final String name;
    private final Brand brand;

    public Model(int id, String name, Brand brand) {
        this.id = id;
        this.name = name;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Brand getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return name;
    }
}
