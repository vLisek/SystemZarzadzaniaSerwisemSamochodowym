package project.app.model;

public class Service {
    private int id;
    private String name;
    private double price;
    private int duration;

    public Service(int id, String name, double price, int duration) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.duration = duration;
    }

    public Service(String name, double price, int duration) {
        this(-1, name, price, duration); // -1 lub 0 jako placeholder
    }

    // Gettery i settery
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
}
