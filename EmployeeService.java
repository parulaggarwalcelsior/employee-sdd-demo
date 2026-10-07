package employee;

import java.util.List;

public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public void addEmployee(Employee employee) {

        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }

        if (employee.getId() <= 0) {
            throw new IllegalArgumentException("Employee ID must be positive");
        }

        if (employee.getName() == null ||
                employee.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Employee name is required");
        }

        if (employee.getEmail() == null ||
                !employee.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }

        if (employee.getSalary() <= 0) {
            throw new IllegalArgumentException("Salary must be greater than zero");
        }

        if (repository.findById(employee.getId()).isPresent()) {
            throw new IllegalArgumentException(
                    "Employee ID already exists");
        }

        repository.save(employee);
    }

    public Employee getEmployee(int id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Employee not found"));
    }

    public void updateSalary(int id, double newSalary) {

        if (newSalary <= 0) {
            throw new IllegalArgumentException(
                    "Salary must be greater than zero");
        }

        Employee employee = getEmployee(id);

        if (!employee.isActive()) {
            throw new IllegalStateException(
                    "Inactive employee salary cannot be updated");
        }

        employee.setSalary(newSalary);
    }

    public void deactivateEmployee(int id) {

        Employee employee = getEmployee(id);
        employee.deactivate();
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }
}
