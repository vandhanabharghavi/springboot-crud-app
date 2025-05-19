package com.vandhana.crudapp.controller;

import com.vandhana.crudapp.model.Employee;
import com.vandhana.crudapp.repository.EmployeeRepository;
import com.vandhana.crudapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private EmployeeRepository repository;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return service.getAll();
    }

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee emp) {
        return repository.save(emp);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee emp) {
        emp.setId(id); // update by ID
        return repository.save(emp);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        service.deleteById(id);
        return "Employee with ID " + id + " deleted successfully.";
    }

    @GetMapping("/by-department/{dept}")
    public List<Employee> getByDepartment(@PathVariable String dept) {
        return service.getAll().stream()
                .filter(emp -> dept.equalsIgnoreCase(emp.getDepartment()))
                .collect(Collectors.toList());
    }
}
