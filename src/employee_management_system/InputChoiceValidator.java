package employee_management_system;
import java.lang.IllegalArgumentException;

public class InputChoiceValidator {
	public static void validateChoice(int choice, int min, int max) {
		if(choice < min || choice > max) {
			throw new IllegalArgumentException("Du lieu nhap vao khong hop le., Choice phai >= " + min +  " va <= " + max + ". IllegalArgumentException!");
		}
	}
}
