package employee_management_system;

public class DuplicateIdException extends Exception{
	public DuplicateIdException() {
		super();
	}
	
	public DuplicateIdException(String message) {
		super(message);
	}
}
