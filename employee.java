package Employee;

public class Employee {

    private int id;
    private String name;
    private String email;
    private String department;
    private double salary;
    private boolean active;

    public Employee(int id, String name, String email,
                    String department, double salary) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.salary = salary;
        this.active = true;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public boolean isActive() {
        return active;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }
}
