package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Employee> getAll() {
            return jdbcTemplate.query("SELECT * from employee", BeanPropertyRowMapper.newInstance(Employee.class));
        };

    public int save(Employee employee) {
        return jdbcTemplate.update("INSERT INTO employee (id, name , salary , department , email , mobile_no) VALUES(?,?,?,?,?,?)",
                employee.getId(), employee.getName(), employee.getSalary(),employee.getDepartment(),employee.getEmail(),employee.getMobile_no());
      }

    public Employee findById(Long id) {
        try {
            Employee employee = jdbcTemplate.queryForObject("SELECT * FROM employee WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Employee.class), id);
            return employee;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM employee WHERE id=?", id);
    }

    public int update(Long id ,Employee employee) {
        return jdbcTemplate.update("UPDATE employee SET name=? , salary =? , department=? , email=? , mobile_no=? WHERE id=?",
                new Object[] { employee.getName(),employee.getSalary(), employee.getDepartment(),employee.getEmail(),employee.getMobile_no(), id });
    }
    @CacheEvict(value="employee", key="#id")
    public int deleteByIdFromRedis(Long id) {
        return jdbcTemplate.update("DELETE FROM employee WHERE id=?", id);
    }


    @Cacheable(value="employee", key="#id")
    public Employee findByIdFromRedis(Long id) {
        try {
            Employee employee = jdbcTemplate.queryForObject("SELECT * FROM employee WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Employee.class), id);
            return employee;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
    @CachePut(value="employee", key="#id")
    public Employee updateEmployeeInRedis(Long id, Employee employee) {
        int response = this.update(id,employee);
        Employee employee1 = this.findByIdFromRedis(id);
        return employee1;
    }
}
