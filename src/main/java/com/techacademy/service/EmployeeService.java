package com.techacademy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    static
    EmployeeRepository employeeRepository;

    public static List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public static Optional<Employee> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

}