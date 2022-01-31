package com.sunglowsys.service;

import com.sunglowsys.domain.Employee;
import com.sunglowsys.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee) {
        return  employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee, Long id) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employee = employeeRepository.findAll();
        return employee;
    }

    @Override
    public Employee findById(Long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;

        if (optional.isPresent()){
            employee = optional.get();
        }
        else {
            throw new RuntimeException("Employee not fount for id:"+id);
        }
        return employee;
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}