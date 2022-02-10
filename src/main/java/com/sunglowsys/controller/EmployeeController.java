package com.sunglowsys.controller;

import com.sunglowsys.domain.Employee;
import com.sunglowsys.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public String home(Model model){
        /*model.addAttribute("employee",employeeService.findAll());
        return "index";*/

        return findByPages(1,model);
    }

    @GetMapping("/search")
    public String getByKeyword(String keyword, Model model){
        if (keyword != null){
            List<Employee> employees = employeeService.findByKeyword(keyword);
            model.addAttribute("employee",employees);
        }
        return "index";
    }

    @GetMapping("/page/{pageNo}")
    public String findByPages(@PathVariable("pageNo") int pageNo, Model model){
        int pageSize = 3;

        Page<Employee> employeePage = employeeService.findByPage(pageNo,pageSize);
        List<Employee> employees = employeePage.getContent();

        model.addAttribute("pageNo",pageNo);
        model.addAttribute("totalPage",employeePage.getTotalPages());
        model.addAttribute("totalElement",employeePage.getTotalElements());
        model.addAttribute("employee",employees);
        return "index";
    }

    @GetMapping("/orderByFirstName")
    public String findAllOrderByFirstName(Model model){
        model.addAttribute("employee",employeeService.findAllOrderByFirstName());
        return "index";
    }

    @GetMapping("/orderBySalary")
    public String findAllOrderBySalary(Model model){
        model.addAttribute("employee",employeeService.findAllOrderBySalary());
        return "index";
    }

    @GetMapping("/addEmp")
    public String addEmployeeForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "add_employee";
    }

    @PostMapping("/saveEmp")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.save(employee);
        return "redirect:/";
    }

    @GetMapping("/updateEmployeeForm/{id}")
    public String updateEmployeeForm(@PathVariable("id") Long id, Model model){
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee",employee);
        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") Long id){
        employeeService.delete(id);
        return "redirect:/";
    }
}
