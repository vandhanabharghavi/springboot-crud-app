package com.vandhana.crudapp.controller;

import com.vandhana.crudapp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.vandhana.crudapp.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping
    public String listEmployees(Model model){
        model.addAttribute("listEmployees",service.getAll());
        return "index";
    }

    @GetMapping("/new")
    public String showNewForm(Model model){
        model.addAttribute("employee",new Employee());
        return "new_employee";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee emp){
        service.save(emp);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        model.addAttribute("employee",service.getById(id));
        return "edit_employee";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id){
        service.deleteById(id);
        return "redirect:/employees";
    }

    @GetMapping("/by-department/{dept}")
    @ResponseBody
    public List<Employee> getByDepartment(@PathVariable String dept){
        return service.getAll().stream().filter(emp->
                dept.equalsIgnoreCase(emp.getDepartment())).collect(Collectors.toList());
    }
}
