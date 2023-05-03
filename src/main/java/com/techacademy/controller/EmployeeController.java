package com.techacademy.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    /** トップページを表示 */
    @GetMapping({"/", "/list"})
    public String getHome(Model model) {
        // 全件検索結果をModelに登録
        model.addAttribute("employeeList", service.getAllEmployees());
        // home.htmlに画面遷移
        return "/employee/list";
    }
    

    @GetMapping("/details/{id}")
    public String getEmployeeDetails(@PathVariable("id") Integer id, Model model) {
        Optional<Employee> employee = java.util.Optional.empty();
		try {
			employee = EmployeeService.getEmployeeById(id);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            return "employee/details";
        } else {
            return "redirect:/employee/list";
        }
    }
 // com.techacademy.controller.EmployeeController

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        EmployeeService.deleteEmployee(id);
        return "redirect:/employee/list";
    }

}
