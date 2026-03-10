package employee_management_system;
import java.util.List;

public interface IEmployeeService {

	void addEmployee (Employee newEmployee) throws DuplicateIdException, DuplicateEmailException;

	List<Employee> getAllEmployee();

	Employee findEmployeeById(String id) throws EmployeeNotFoundException;
	
	void deleteEmployee(String id) throws EmployeeNotFoundException;

	List<Employee> getTopHighestSalaryEmployees();
	
}
