package com.techacademy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techacademy.entity.Authentication;
import com.techacademy.entity.Employee;
import com.techacademy.repository.AuthenticationRepository;
import com.techacademy.repository.EmployeeRepository;

@Service
public class EmployeeDetailService implements UserDetailsService {
    private final AuthenticationRepository authenticationRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeDetailService(AuthenticationRepository authenticationRepository, EmployeeRepository employeeRepository) {
        this.authenticationRepository = authenticationRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String code) throws UsernameNotFoundException {
        Optional<Authentication> authentication = authenticationRepository.findById(code);

        if (!authentication.isPresent()) {
            throw new UsernameNotFoundException("Exception:Username Not Found");
        }
        Employee employee = employeeRepository.findByAuthentication(authentication.get());
        return new EmployeeDetail(employee, authentication.get());
    }
}
