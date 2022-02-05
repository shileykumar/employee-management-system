package com.sunglowsys.service;

import com.sunglowsys.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {

    Employee save(Employee employee);

    Employee update(Employee employee, Long id);

    List<Employee> findAll();

    List<Employee> findByKeyword(String keyword);

    List<Employee> findAllOrderByFirstName();

    List<Employee> findAllOrderBySalary();

    /*Page<Employee> findByPage(int pageNo, int pageSize);*/

    Employee findById(Long id);

    void delete(Long id);
}
