package com.mindtree.employeeapp.service;

import java.util.List;

import com.mindtree.employeeapp.entity.Employee;
import com.mindtree.employeeapp.exceptions.ServiceException;

public interface EmployeeService {
	List<Employee> getallEmployees() throws ServiceException;
	Employee addEmployee(Employee employee) throws ServiceException;
	Employee updatEmployee(Long id,Employee employee) throws ServiceException;
	Long deleteEmployee(Long id)throws ServiceException;
	Employee getEmployee(Long id)throws ServiceException;

}
