package com.interview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.interview.entity.Employee;
import com.interview.exceptions.EmployeeNotFoundException;
import com.interview.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository empRepository;
	
	@PostMapping("/employee/create")
	public void createUser(@RequestBody Employee employee){
		int id = employee.getId();
		Employee emp = empRepository.findById(id);
		if(emp!=null) {
			throw new EmployeeNotFoundException("Employee with id already exists!!!");  
		}else {
			empRepository.insert(employee);
		}
		
	}
	
	@GetMapping("/employee/{ID}")
	public Employee getEmployeeById(@PathVariable int ID) {
		Employee emp = empRepository.findById(ID);
		if(emp!=null) {
			return emp;
		}else {
			throw new EmployeeNotFoundException("Employee with id doesnot exist!!!" + ID);
		}
	}
	
	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		List<Employee> empList = empRepository.findAll();
		if(!empList.isEmpty() && empList.size()>0) {
			return empList;
		}else {
			throw new EmployeeNotFoundException("No records found!!");
		}
		
	}
	
	@DeleteMapping("/employee/{ID}")
	public void removeEmployee(@PathVariable int ID) {
		Employee emp = empRepository.findById(ID);
		if(emp==null) {
			throw new EmployeeNotFoundException("Employee doesnot exist");  
		}else {
			empRepository.deleteById(ID);
		}
		
	}
}
