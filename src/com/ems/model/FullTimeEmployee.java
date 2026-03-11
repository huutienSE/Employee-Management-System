package com.ems.model;

import java.text.DecimalFormat;

public class FullTimeEmployee extends Employee {
	private double bonus;
	
	FullTimeEmployee(String id, String name, double salary, String email, String phoneNumber, double bonus){
		super(id, name, salary, email, phoneNumber);
		this.bonus = bonus;
	}
	
	@Override
	public double caculateSalary() {
		return super.salary + bonus;
	}
	
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#,###.00");
		return "Id: " + id + 
				" Name: " + name + 
				" Base Salary: " + df.format(salary) +
				" Email: " + email +
				" Phone Number: " + phoneNumber + 
				" Salary: " + df.format(this.caculateSalary());
	}
}
