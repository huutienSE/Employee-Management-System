# EMPLOYEE MANAGEMENT SYSTEM

This is a simple Java Backend application for employee management.
The project was built with the main purpose of reviewing Java OOP, the Collection Framework, Exception Handling, applying a layered architecture model, and Git workflow.

## Project Overview

The Employee Management System allows users to manage employee information for two types of employees: Full-time and Part-time.

The system supports CRUD operations and several data control features.

### Main functionalities include:
    - Add new employees
    - Update employee information
    - Delete employees
    - Search employees
    - Sort employees by salary

## Architecture

The project follows a Layered Architecture, which divides the system into multiple layers to improve maintainability and scalability.

The layers used in this project include:

- Controller Layer – Handles user interaction
- Service Layer – Contains business logic
- Model Layer – Represents data structures
- Exception Layer – Custom exception handling
- Utility Layer – Helper classes

## Project Structure

'''
src/com/ems
│
├── controller
│   └── Main.java
│
├── model
│   ├── Employee.java
│   ├── FullTimeEmployee.java
│   └── PartTimeEmployee.java
│
├── service
│   ├── IEmployeeService.java
│   └── EmployeeManager.java
│
├── exception
│   ├── DuplicateEmailException.java
│   ├── DuplicateIdException.java
│   └── EmployeeNotFoundException.java
│
└── util
    ├── InputChoiceValidator.java
    └── SalaryComparator.java
'''


## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Layered Architecture
- Git & GitHub

## Challenges & Solutions

### 1. Designing Layered Architecture

**Challenge**
When using a typical Java console project structure, many files are often placed in the same package.
This can lead to very long and complex files, making the project difficult to maintain and test.

**Solution**
The project was refactored using a layered architecture, separating the code into different packages:

- controller
- service
- model
- exception
- util

This separation improves code readability and scalability.

### 2. Handling Duplicate Employee Data

**Challenge**
Preventing duplicate employee IDs and emails.

**Solution**
Custom exceptions were implemented:

- DuplicateIdException
- DuplicateEmailException

These exceptions ensure data integrity in the system.

### 3. Console User Experience (UX)

**Challenge**
In a console application, if a user enters an invalid value for one field, forcing them to re-enter all employee information from the beginning can be frustrating.

**Solution**
A while(true) loop combined with custom exceptions is used to handle invalid input.
This ensures that users only need to re-enter the incorrect information (for example: duplicate ID or invalid email).

Additionally, a control variable is used to limit the maximum number of invalid input attempts, preventing the program from running into an infinite loop during the input process.

### 4. Refactoring Project Structure

**Challenge**
The initial project structure did not follow a clean and organized architecture.

**Solution**
The project was refactored based on a layered architecture and reorganized into the com.ems package structure.

## How to Run

1. Clone the repository
2. Open the project in your IDE (Eclipse / IntelliJ)
3. Run the 'Main.java' file

## Future Improvements

This project will be improved with:

- MySQL database integration
- Repository layer implementation
- REST API using Spring Boot
- Frontend integration
- Authentication system

## Author
**Huu Tien**
GitHub:
https://github.com/huutienSE