package com.mindtree.employeeapp.dao;

import java.util.List;

import com.mindtree.employeeapp.entity.Employee;
import com.mindtree.employeeapp.exceptions.DaoException;

public interface DaoService {
	List<Employee> getallEmployees() throws DaoException;
	Employee addEmployee(Employee employee) throws DaoException;
	Employee updateEmployee(Employee employee)throws DaoException;
	Long deleteEmployee(Long id) throws DaoException;
	Employee getEmployee(Long id)throws DaoException;

}
