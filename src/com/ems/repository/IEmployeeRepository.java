package com.ems.repository;

import com.ems.model.Employee;
import java.util.List;


public interface IEmployeeRepository {
	void save(Employee employee);
	
	List<Employee> findAll();
	
	Employee findById(int id);

    void delete(int id);
}
