package com.ems.model;
import java.text.DecimalFormat;

public abstract class Employee implements Comparable<Employee> {
	protected String id;
	protected String name;
	protected double salary; // luong cơ bản
	protected String email;
	protected String phoneNumber;
	
	// abstract class
	
	Employee(String id, String name, double salary, String email, String phoneNumber){
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public String getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setId(String newId) {
		this.id = newId;
	}
	
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}
	
	public abstract double caculateSalary(); // lương thực lĩnh

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#,###.00");
		return "Id: " + id + 
				" Name: " + name + 
				" Base Salary: " + df.format(salary) +
				" Email: " + email +
				" Phone Number: " + phoneNumber;
	}
	
	@Override
	public int compareTo(Employee e) {
		return this.getId().compareTo(e.getId());
	}
	
}
