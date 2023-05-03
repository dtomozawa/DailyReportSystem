package com.techacademy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

    @Autowired
	static
    EmployeeRepository employeeRepository;

    public List<Employee> getEmployeeList() {
        return employeeRepository.findAll();
    }

	public Object getAllEmployees() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public static Optional<Employee> getEmployeeById(Integer id) {
	    return employeeRepository.findById(id);
	}
	
	public static Employee saveEmployee(Employee employee) {
	    return employeeRepository.save(employee);
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
	public static void deleteEmployee(Integer id) {
	    employeeRepository.deleteById(id);
	}
}


