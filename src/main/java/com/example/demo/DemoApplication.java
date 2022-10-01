package com.example.demo;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EnableCaching
@SpringBootApplication
public class DemoApplication implements  CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Spring boot application with vertica and redis !!!");
		//List<Employee> employeeList = employeeRepository.getAll();
		//System.out.println("Employee List : "+ Arrays.toString(employeeList.stream().toArray()));
		//int response = employeeRepository.save(new Employee(5,"Lol"));
		//System.out.println("reponse : "+response);
	}
}
