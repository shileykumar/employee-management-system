package com.sunglowsys.repository;


import com.sunglowsys.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("from Employee emp where emp.firstName like %:keyword% " +
            "or emp.lastName like %:keyword% or emp.gender like %:keyword% ")
    List<Employee> getByKeyword(@Param("keyword") String keyword);
}
