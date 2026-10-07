package employee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    @Test
    void shouldAddEmployeeSuccessfully() {

        EmployeeRepository repository = new EmployeeRepository();
        EmployeeService service = new EmployeeService(repository);

        Employee employee = new Employee(
                101,
                "Parul",
                "parul@example.com",
                "QA",
                75000
        );

        service.addEmployee(employee);

        Employee result = service.getEmployee(101);

        assertEquals("Parul", result.getName());
        assertEquals("QA", result.getDepartment());
        assertEquals(75000, result.getSalary());
        assertTrue(result.isActive());
    }

    @Test
    void shouldRejectDuplicateEmployeeId() {

        EmployeeRepository repository = new EmployeeRepository();
        EmployeeService service = new EmployeeService(repository);

        Employee first = new Employee(
                101, "John",
                "john@example.com",
                "QA", 60000
        );

        Employee second = new Employee(
                101, "David",
                "david@example.com",
                "IT", 70000
        );

        service.addEmployee(first);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.addEmployee(second)
        );
    }

    @Test
    void shouldRejectInvalidEmail() {

        EmployeeRepository repository = new EmployeeRepository();
        EmployeeService service = new EmployeeService(repository);

        Employee employee = new Employee(
                102,
                "David",
                "invalid-email",
                "IT",
                70000
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> service.addEmployee(employee)
        );
    }

    @Test
    void shouldNotUpdateSalaryForInactiveEmployee() {

        EmployeeRepository repository = new EmployeeRepository();
        EmployeeService service = new EmployeeService(repository);

        Employee employee = new Employee(
                103,
                "Sarah",
                "sarah@example.com",
                "HR",
                65000
        );

        service.addEmployee(employee);
        service.deactivateEmployee(103);

        assertThrows(
                IllegalStateException.class,
                () -> service.updateSalary(103, 80000)
        );
    }
}
