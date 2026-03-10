package employee_management_system;

public class DuplicateEmailException extends Exception {
	public DuplicateEmailException() {
		super();
	}
	
	public DuplicateEmailException(String message) {
		super(message);
	}
}
