package employee_management_system;
import java.util.List;

// dùng List thay vì các implements, nó sexdungf abSTRACTTRONG, sau này có thể đổi các ctdl khác trong List interface

public interface IEmployeeService {
	// them nhan vien
	void addEmployee (Employee newEmployee) throws DuplicateIdException, DuplicateEmailException;
	
	// Hiển thị tất cả nhân viên
	List<Employee> getAllEmployee();
	//void displayAllEmployee(ArrayList<Employee> listEmployee); tầng service chỉ nên xử lý lấy danh sách nhân viên ra thôi, còn in sẽ để cho phần UI
	
	// tìm kiếm nhân viên theo ID
	Employee findEmployeeById(String id) throws EmployeeNotFoundException;
	
	// Xóa nhân viên
	void deleteEmployee(String id) throws EmployeeNotFoundException;
	
	// Xuất danh sách nhân viên có lương cao nhất (top 5)
	List<Employee> getTopHighestSalaryEmployees();
	
}
