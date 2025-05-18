package com.vandhana.crudapp.repository;

import com.vandhana.crudapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
