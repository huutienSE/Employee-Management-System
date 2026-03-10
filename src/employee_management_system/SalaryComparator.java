package employee_management_system;
import java.util.Comparator;

public class SalaryComparator implements Comparator<Employee> {
	@Override
	public int compare(Employee e1, Employee e2) {
		return Double.compare(e2.caculateSalary(), e1.caculateSalary());
	}
}
