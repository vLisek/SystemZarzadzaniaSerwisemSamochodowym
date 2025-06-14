package project.app.model;

import java.time.LocalDate;

public class Order {
    private int orderId;
    private Customer customer;
    private VehicleDisplay vehicle;
    private Employee employee;
    private Service service;
    private Parts parts;
    private LocalDate deadline;
    private String status;
    private String description;
    private double totalCost;

    public Order() {

    }

    public Order(int orderId, Customer customer, VehicleDisplay vehicle, Employee employee, Service service, Parts parts, LocalDate deadline, String status, String description, double totalCost) {
        this.orderId = orderId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.employee = employee;
        this.service = service;
        this.parts = parts;
        this.deadline = deadline;
        this.status = status;
        this.description = description;
        this.totalCost = totalCost;
    }

    public Order(int orderId, String description, LocalDate deadline, String status) {
        this.orderId = orderId;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order #" + orderId + ": " + description + " (deadline: " + deadline + ")";
    }


    public VehicleDisplay getVehicle() {
        return vehicle;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public VehicleDisplay getCar() {
        return vehicle;
    }

    public void setCar(VehicleDisplay vehicle) {
        this.vehicle = vehicle;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Parts getPart() {
        return parts;
    }

    public void setPart(Parts parts) {
        this.parts = parts;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
