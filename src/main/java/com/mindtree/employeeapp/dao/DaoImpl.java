package com.mindtree.employeeapp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.engine.query.spi.sql.NativeSQLQueryCollectionReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mindtree.employeeapp.entity.Employee;
import com.mindtree.employeeapp.exceptions.DaoException;


@Repository("EmployeeDaoImpl")
public class DaoImpl implements DaoService{
	private JdbcTemplate jdbcTemplate;
	
	
	@Autowired
	public DaoImpl(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	//get all employees
	@Override
	public List<Employee> getallEmployees()throws DaoException {
		// TODO Auto-generated method stub
		String sql = "select * from employee";
		List<Employee> employees;
		try {
			employees = this.jdbcTemplate.query(sql,new RowMapper<Employee>() {

				@Override
				public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					Employee employee = new Employee();
					employee.setId(rs.getLong("id"));
					employee.setName(rs.getString("name"));
					employee.setDesignation(rs.getString("designation"));
					return employee;
				}
				
			});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}
		return employees;
	}
	
	//add employee
	@Override
	public Employee addEmployee(Employee employee) throws DaoException{
		// TODO Auto-generated method stub
		String sql = "insert into employee (id,name,designation) values (?,?,?)";
		try {
			jdbcTemplate.update(sql,new Object[] {employee.getId(),employee.getName(),employee.getDesignation()});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		
		}
		return employee;
	}

	//update employee by id
	@Override
	public Employee updateEmployee(Employee employee) throws DaoException {
		// TODO Auto-generated method stub
		String sql = "update employee set name = ? where id = ?";
		int result;
		try {
			result = jdbcTemplate.update(sql,new Object[]{employee.getName(),employee.getId()});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}
		return result == 1 ? employee : null;
		//ternary operation
	}
	
	//delete employee by id
	@Override
	public Long deleteEmployee(Long id) throws DaoException {
		// TODO Auto-generated method stub
		String sql = "delete from employee where id = ?";
		int result;
		try {
			result = jdbcTemplate.update(sql,new Object[] {id});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}
		return result == 1 ? id : null;
	}

	//get employee by id
	@Override
	public Employee getEmployee(Long id) throws DaoException {
		// TODO Auto-generated method stub
		String sql = "select * from employee where id = ?";
		try {
			try {
				Employee emp  = jdbcTemplate.queryForObject(sql,new Object[] {id},new RowMapper<Employee>() {

					@Override
					public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						Employee employee = new Employee();
						employee.setId(rs.getLong("id"));
						employee.setName(rs.getString("name"));
						employee.setDesignation(rs.getString("designation"));
						return employee;
					}
					
				});
				return emp;
			} catch (EmptyResultDataAccessException e) {
				// TODO Auto-generated catch block
				return null;			
			}
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}
		
	}

}
