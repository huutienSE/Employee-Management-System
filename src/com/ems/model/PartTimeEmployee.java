package com.ems.model;

import java.text.DecimalFormat;

public class PartTimeEmployee extends Employee {
	private double workingHours;
	private double rate; // luong theo gio
	
	PartTimeEmployee(String id, String name, double salary, String email, String phoneNumber, double workingHours, double rate){
		super(id, name, salary, email, phoneNumber);
		this.workingHours = workingHours;
		this.rate = rate;
	}
	
	@Override
	public double caculateSalary() {
		return super.salary + (workingHours * rate);
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
