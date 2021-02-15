package com.mindtree.employeeapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.employeeapp.entity.Employee;
import com.mindtree.employeeapp.exceptions.ServiceException;
import com.mindtree.employeeapp.service.EmployeeService;


@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/all")
	@ResponseBody
	public List<Employee> getallEmployees(){
		List<Employee> employees = null;
		try {
			employees = employeeService.getallEmployees();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employees;
	}
	
	
	@PostMapping("/add")
	public ResponseEntity addEmployee(@RequestBody Employee employee){
		try {
			employeeService.addEmployee(employee);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>("DATABASE ERROR",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("employee added",HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity updateEmployee(@PathVariable Long id,@RequestBody Employee employee) {
		try {
			if(null == employeeService.updatEmployee(id,employee)) {
				return new ResponseEntity<>("No employee found for id :"+id,HttpStatus.NOT_FOUND);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>("DATABASE ERROR",HttpStatus.BAD_REQUEST);
		
		}
		
		return new ResponseEntity<>("updated with employee id :"+id,HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity deleteEmployee(@PathVariable Long id) {
		try {
			if(null == employeeService.deleteEmployee(id)) {
				return new ResponseEntity<>("No employee found for id :"+id,HttpStatus.NOT_FOUND);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>("DATABASE ERROR",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("deleted with employee id :"+id,HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id){
		Employee employee;
		try {
			employee = employeeService.getEmployee(id);
			if(employee == null) {
				return new ResponseEntity("No employee found for id :"+id,HttpStatus.NOT_FOUND);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity("DATABASE ERROR",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Employee>(employee,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public String getMessage() {
		return "[GET] https//localhost:8080/api/v1/all ->  gives list of employees\n"
				+ "[GET] https://localhost:8080/api/v1/get/{id} -> gives employee details based on ID\n"
				+ "[POST] https://localhost:8080/api/v1/add - > add employee\n"
				+ "[PUT] https://localhost:8080/api/v1/update/{id} - > updates employee name based on ID\n"
				+ "[DELETE] https://localhost:8080/api/v1/delete/{id} -> delete employee based on ID";
	}
	

}
