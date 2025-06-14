package project.app.model;

public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String position;
    private String login;
    private String password;
    private String roleName;

    public Employee() {}

    public Employee(int employeeId, String firstName, String lastName,
                    String phoneNumber, String position, String login,
                    String password, String roleName) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.login = login;
        this.password = password;
        this.roleName = roleName;
    }

    public Employee(int employeeId, String firstName, String lastName) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getDisplayName() {
        return firstName + " " + lastName + " – " + position;
    }

    @Override
    public String toString() {
        return getDisplayName();
    }


    public int getEmployeeId() {
        return employeeId;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }


    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
