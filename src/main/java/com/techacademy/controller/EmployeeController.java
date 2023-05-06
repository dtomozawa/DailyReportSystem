package com.techacademy.controller;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String getList(Model model) {
        model.addAttribute("employeelist", service.getEmployeeList());
        return "employee/list";
    }

    @GetMapping("/create")
    public String getRegister(@ModelAttribute Employee employee) {
        return "employee/create";
    }

    @PostMapping("/create")
    public String postRegister(@Validated Employee employee, BindingResult res, Model model) {
        if(res.hasErrors()) {
            return getRegister(employee);
        }
        service.saveEmployee(employee);
        return "redirect:/employee/list";
    }

    @GetMapping("/update/{id}/")
    public String getEmployee(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("employee", service.getEmployeeById(id));
        return "employee/update";
    }

    @PostMapping("/update/{id}/")
    public String postEmployee(Employee employee) {
        service.saveEmployee(employee);
        return "redirect:/employee/list";
    }

    @PostMapping(path="list", params="deleteRun")
    public String deleteRun(@RequestParam(name="idck") Integer idck, Model model) {
        service.deleteEmployee(idck);
        return "redirect:/employee/list";
    }
}