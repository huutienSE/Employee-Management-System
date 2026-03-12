package com.ems.util;
import java.util.Comparator;

import com.ems.model.Employee;

public class SalaryComparator implements Comparator<Employee> {
	@Override
	public int compare(Employee e1, Employee e2) {
		return Double.compare(e2.caculateSalary(), e1.caculateSalary());
	}
}
