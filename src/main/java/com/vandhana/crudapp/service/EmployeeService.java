package com.vandhana.crudapp.service;

import com.vandhana.crudapp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vandhana.crudapp.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;
    public List<Employee> getAll(){
        return repository.findAll();
    }
    public void save(Employee employee){
        repository.save(employee);
    }
    public Employee getById(Long id){
        return repository.findById(id).orElse(null);
    }
    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
