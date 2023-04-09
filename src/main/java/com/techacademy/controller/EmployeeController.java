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
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping("/list")
    public String getEmployeeList(Model model) {
        model.addAttribute("employees", EmployeeService.getAllEmployees());
        return "employee/list";
    }

    @GetMapping("/details/{id}")
    public String getEmployeeDetails(@PathVariable("id") Integer id, Model model) {
        Optional<Employee> employee = EmployeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            return "employee/details";
        } else {
            return "redirect:/employee/list";
        }
    }
    @GetMapping("/create")
    public String getCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/create";
    }

    @PostMapping("/create")
    public String createEmployee(@ModelAttribute Employee employee) {
        EmployeeService.saveEmployee(employee);
        return "redirect:/employee/list";
    }

    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<Employee> employee = EmployeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            return "employee/update";
        } else {
            return "redirect:/employee/list";
        }
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Employee employee) {
        EmployeeService.saveEmployee(employee);
        return "redirect:/employee/list";
    }
}
