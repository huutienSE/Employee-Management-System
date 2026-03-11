package employee_management_system;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import com.ems.model.Employee;

import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;


public class EmployeeManager implements IEmployeeService {
	private final List<Employee> listEmployees = new ArrayList<>();
	private final Map<String, Employee> employeeMap = new HashMap<>();
	private final Set<String> emailsSet = new HashSet<>();
	
	
	@Override
	public void addEmployee(Employee newEmployee) throws DuplicateIdException, DuplicateEmailException {
		// kiem tra nhan vien trùng lặp
		if(employeeMap.containsKey(newEmployee.getId())) {
			throw new DuplicateIdException("Id da ton tai!");
		}
		
		if(emailsSet.contains(newEmployee.getEmail())) {
			throw new DuplicateEmailException("Email da ton tai!");
		}
		
		if(newEmployee == null) {
			throw new IllegalArgumentException("Nhan vien khong the null!");
		}
		
		listEmployees.add(newEmployee);
		employeeMap.put(newEmployee.getId(), newEmployee);
		emailsSet.add(newEmployee.getEmail());
	}

	@Override
	public List<Employee> getAllEmployee() {
		// danh sách in ra sẽ mặc định in tăng dần theo ID
		List<Employee> sortList = new ArrayList<Employee>(listEmployees);
		
		Collections.sort(sortList);
		
		return sortList;
	}

	@Override
	public Employee findEmployeeById(String id) throws EmployeeNotFoundException  {
		// uu diem chi can duyt map 1 lan
		Employee emp = employeeMap.get(id);

		if(emp == null){
		    throw new EmployeeNotFoundException("Khong tim thay nhan vien co id: " + id);
		}

		return emp;
	}

	@Override
	public void deleteEmployee(String id) throws EmployeeNotFoundException{
		
		Employee deleteEmployee = findEmployeeById(id);
		
		listEmployees.remove(deleteEmployee);
		
		employeeMap.remove(id);
		
		emailsSet.remove(deleteEmployee.getEmail());
	}

	@Override
	public List<Employee> getTopHighestSalaryEmployees() {
		
		List<Employee> sortedList = new ArrayList<>(listEmployees);
	
		Collections.sort(sortedList, new SalaryComparator());
		
		return sortedList;
	}
	
}
