package com.mindtree.employeeapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.employeeapp.dao.DaoImpl;
import com.mindtree.employeeapp.entity.Employee;
import com.mindtree.employeeapp.exceptions.DaoException;
import com.mindtree.employeeapp.exceptions.ServiceException;


@Service("employeeservice")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private DaoImpl daoImpl;
	
	@Override
	public List<Employee> getallEmployees() throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return daoImpl.getallEmployees();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	@Override
	public Employee addEmployee(Employee employee) throws ServiceException{
		// TODO Auto-generated method stub
		try {
			return daoImpl.addEmployee(employee);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	@Override
	public Employee updatEmployee(Long id,Employee employee) throws ServiceException{
		// TODO Auto-generated method stub
		employee.setId(id);
		try {
			return daoImpl.updateEmployee(employee);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	@Override
	public Long deleteEmployee(Long id)throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return daoImpl.deleteEmployee(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	@Override
	public Employee getEmployee(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return daoImpl.getEmployee(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

}
