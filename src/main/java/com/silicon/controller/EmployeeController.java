package com.silicon.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.silicon.entity.Employee;
import com.silicon.repo.EmployeeRepo;

@RestController
public class EmployeeController {

	 @Autowired 
	 private EmployeeRepo repo;
	 
	 @GetMapping("/details")
	 public List<Employee> getEmployee()
	 {
		return  repo.findAll();
	 }
	 
	 @PostMapping("/insert")
	 public String insertData( @Validated @RequestBody Employee employee)
	 {
		 System.out.println(employee.getName());
		  repo.save(employee);
		 
		 return "Employee Data is Saved With Generated ID Value " +employee.getId();
	 }
	 
	 
	 @GetMapping("/find/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id)
	{
		Employee employee= repo.findById(id).orElseThrow(()-> new IllegalArgumentException("Id is Not Found  to retrive data"));
		return ResponseEntity.ok(employee);
	}
	 
	 @PutMapping("/update/{id}")
	 public ResponseEntity<Employee> updateEmployeeById(@PathVariable Integer id, @RequestBody Employee employee) 
	 {
		 Employee emp= repo.findById(id).orElseThrow(()-> new IllegalArgumentException("Given id is not found to Update Data "));
		 
		 emp.setName(employee.getName());
		 emp.setAddress(employee.getAddress());
		 emp.setSalary(employee.getSalary());
		 
		 Employee update= repo.save(emp);
		 
		 return ResponseEntity.ok(update);
	 }
	 
	 @DeleteMapping("delete/{id}")
	 public String deleteById(@PathVariable Integer id, Employee emp)
	 {
		 repo.findById(id).orElseThrow(()-> new IllegalArgumentException("Given id not found to delete the data"));
		 
		 repo.deleteById(id);
		 return "Enployee is deleted for the id " +id;
	 }
	 
}
