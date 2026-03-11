package com.ems.service;
import java.util.List;

import com.ems.model.Employee;

import employee_management_system.DuplicateEmailException;
import employee_management_system.DuplicateIdException;

public interface IEmployeeService {

	void addEmployee (Employee newEmployee) throws DuplicateIdException, DuplicateEmailException;

	List<Employee> getAllEmployee();

	Employee findEmployeeById(String id) throws EmployeeNotFoundException;
	
	void deleteEmployee(String id) throws EmployeeNotFoundException;

	List<Employee> getTopHighestSalaryEmployees();
	
}
