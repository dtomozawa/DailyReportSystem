package com.techacademy.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Employee;
import com.techacademy.service.EmployeeService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping({ "/", "/list" })
    public String getHome(Model model) {
        model.addAttribute("employeeList", service.getAllEmployees());
        return "employee/list";
    }

    @GetMapping("/create")
    public String getRegister(@ModelAttribute Employee employee) {
        return "employee/create";
    }


    @GetMapping("/details/{id}")
    public String getEmployeeDetails(@PathVariable("id") Integer id, Model model) {
        Optional<Employee> employee = service.getEmployeeById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            return "employee/details";
        } else {
            return "redirect:/employee/list";
        }
    }
    
    @GetMapping("/update/{id}")
    public String getEmployee(@PathVariable("id") Integer id, Model model) {
        Optional<Employee> employee = service.getEmployeeById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            return "employee/update";
        } else {
            return "redirect:/employee/list";
        }
    }
    
    @PostMapping("/create")
    public String postRegister(Employee employee) {
        service.saveEmployee(employee);
        return "redirect:/employee/list";
    }
    
    @PostMapping("/update/{id}")
    public String postUser(Employee employee) {
        service.saveEmployee(employee);
        return "redirect:/employee/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        service.deleteEmployee(id);
        return "redirect:/employee/list";
    }

 
 
}
