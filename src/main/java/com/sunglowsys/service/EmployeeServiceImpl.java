package com.sunglowsys.service;

import com.sunglowsys.domain.Employee;
import com.sunglowsys.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<Employee> findByKeyword(String keyword) {
        List<Employee> employees = employeeRepository.getByKeyword(keyword);
        return employees;
    }

    @Override
    public List<Employee> findAllOrderByFirstName() {
        List<Employee> employees = employeeRepository.findAll(Sort.by("firstName"));
        return employees;
    }

    @Override
    public List<Employee> findAllOrderBySalary() {
        List<Employee> employees = employeeRepository.findAll(Sort.by("salary"));
        return employees;
    }

    @Override
    public Page<Employee> findByPage(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return employeeRepository.findAll(pageable);
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
