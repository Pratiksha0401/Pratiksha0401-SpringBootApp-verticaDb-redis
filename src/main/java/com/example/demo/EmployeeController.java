package com.example.demo;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping(path = "/get")
    public ResponseEntity<List<Employee>> getAllUserNames() {
        List<Employee> employeeList=  employeeRepository.getAll();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @PostMapping("/vertica")
    public ResponseEntity<String> save(@RequestBody Employee employee){
        int response = employeeRepository.save(employee);
        return new ResponseEntity<>("Employee details added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/vertica/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id);
        return  new ResponseEntity<>(employee,HttpStatus.OK);
    }

    @DeleteMapping("/vertica/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        int response = employeeRepository.deleteById(id);
        return new ResponseEntity<>("Employee deleted successfully" , HttpStatus.OK);
    }

    @PutMapping("/vertica/{id}")
    public ResponseEntity<String> update(@PathVariable Long id , @RequestBody Employee employee){
        Employee employee1= employeeRepository.findById(id);
        if(employee1 !=null){
            employee1.setName(employee.getName());
            employee1.setSalary(employee.getSalary());
            employee1.setDepartment(employee.getDepartment());
            employee1.setEmail(employee.getEmail());
            employee1.setMobile_no(employee.getMobile_no());
            int response =  employeeRepository.update(id ,employee1);
            return new ResponseEntity<>("Employee details updated successfully", HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/vertica/redis/{id}")
    public ResponseEntity<Employee> getEmployeeByIdFromRedis(@PathVariable Long id) {
        Employee employee = employeeRepository.findByIdFromRedis(id);
        return  new ResponseEntity<>(employee,HttpStatus.OK);
    }

    @DeleteMapping("/vertica/redis/{id}")
    public ResponseEntity<String> deleteByIdFromRedis(@PathVariable Long id){
        int response = employeeRepository.deleteByIdFromRedis(id);
        return new ResponseEntity<>("Employee deleted successfully" , HttpStatus.OK);
    }

    @PutMapping("/vertica/redis/{id}")
    public ResponseEntity<String> updateInRedis(@PathVariable Long id , @RequestBody Employee employee){
        Employee employee1= employeeRepository.findById(id);
        if(employee1 !=null){
            employee1.setName(employee.getName());
            employee1.setSalary(employee.getSalary());
            employee1.setDepartment(employee.getDepartment());
            employee1.setEmail(employee.getEmail());
            employee1.setMobile_no(employee.getMobile_no());
            Employee employee2 =  employeeRepository.updateEmployeeInRedis(id ,employee1);
            return new ResponseEntity<>("Employee details updated successfully", HttpStatus.OK);
        }
        return null;
    }
}
