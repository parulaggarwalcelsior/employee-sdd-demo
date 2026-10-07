package employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository {

    private final List<Employee> employees = new ArrayList<>();

    public void save(Employee employee) {
        employees.add(employee);
    }

    public Optional<Employee> findById(int id) {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst();
    }

    public List<Employee> findAll() {
        return new ArrayList<>(employees);
    }

    public void delete(Employee employee) {
        employees.remove(employee);
    }
}
