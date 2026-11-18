
package com.example.employeemanagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private List<Employee> employees = new ArrayList<>();

    // (b) Add a new employee
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        employees.add(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    // (c) Get all employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employees);
    }

    // (c) Get employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                return ResponseEntity.ok(emp);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // (d) Update employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable int id,
            @RequestBody Employee updatedEmployee) {

        for (Employee emp : employees) {
            if (emp.getId() == id) {
                emp.setName(updatedEmployee.getName());
                emp.setDepartment(updatedEmployee.getDepartment());
                emp.setSalary(updatedEmployee.getSalary());

                return ResponseEntity.ok(emp);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // (e) Delete employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {

        for (Employee emp : employees) {
            if (emp.getId() == id) {
                employees.remove(emp);
                return ResponseEntity.ok("Employee deleted successfully!");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Employee not found!");
    }
}
