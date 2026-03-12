package employee_management_system;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.ems.exception.DuplicateEmailException;
import com.ems.exception.DuplicateIdException;
import com.ems.exception.EmployeeNotFoundException;
import com.ems.model.Employee;
import com.ems.model.FullTimeEmployee;
import com.ems.model.PartTimeEmployee;
import com.ems.service.EmployeeManager;

import java.util.List;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		
		boolean end = true;
		
		EmployeeManager manager = new EmployeeManager();
		
		while(end) {
			System.out.println("Welcome to Employee Management System!");
			System.out.println();
			System.out.println("1. Them nhan vien");
			System.out.println("2. Hien thi tat ca nhan vien.");
			System.out.println("3. Tim kiem nhan vien theo ID.");
			System.out.println("4. Xoa nhan vien theo ID.");
			System.out.println("5. Xuat danh sach sinh vien co luong cao nhat.");
			System.out.println("6. Thoat");
			
			int choice;
			int MIN_CHOICE = 1, MAX_CHOICE = 7;
			
			Scanner sc = new Scanner(System.in);
			
			
			
			while(true) {
				try{
					System.out.print("Nhap vao lua chon cua ban: ");
					choice = Integer.parseInt(sc.nextLine());
					// validate choice
					InputChoiceValidator.validateChoice(choice, MIN_CHOICE, MAX_CHOICE);
					
					break;
				}
				catch(NumberFormatException e1) {
					System.out.println("Du lieu nhap vao khong hop le. Ban phai nhap vao chu so!");
				}
				catch(IllegalArgumentException e2) {
					System.out.println(e2.getMessage());
				}
			}
			
			System.out.println("Nhap choice thanh cong, Hop le!");

			switch(choice) {
				case 1:
				{
					System.out.println("Nhap vao thông tin nhan vien: ");
					System.out.println("Ban muon them nhan vien Full-time hay Part-time?");
					System.out.println("Nhap 1. Full-time \t 2. Part-time");
					
					int option = Main.inputEmployeeType(sc);
					
					Employee newEmployee = Main.inputEmployeeInformation(option, sc);
					
					boolean isSuccess = Main.handleDuplicateEmployee(newEmployee, manager,  sc);
					
					if(isSuccess) {
						System.out.println("Them nhan vien THANH CONG!");
					}else {
						System.out.println("Them nhan vien THAT BAI!");
					}
					
					break;
				}
				case 2:
				{
					List<Employee> listEmployees = manager.getAllEmployee();
					
					if(listEmployees.isEmpty()) {
						System.out.println("DANH SACH TRONG!");
					}
					else{
						System.out.println("DANH SACH NHAN VIEN");
						for(Employee e :listEmployees) {
							System.out.println(e.toString());
							System.out.println();
						}
					}
					break;
				}
				case 3:
				{
					System.out.print("Nhap vao id nhan vien muon tim kiem: ");
					String id = sc.nextLine();
					System.out.println();
					
					try {
						Employee employee = manager.findEmployeeById(id);
						
						System.out.println(employee);
					}
					catch(EmployeeNotFoundException e) {
						System.out.println(e.getMessage());
					}
					
					break;
				}
				case 4:
				{
					try {
						System.out.println("XOA NHAN VIEN");
						System.out.print("Nhap vao id nhan vien muon xoa: ");
						String id = sc.nextLine();
						
						Employee deleteEmployee = null;
						// tim kiem nhan vien
						deleteEmployee = manager.findEmployeeById(id);
						
						// in thong tin nhan vien muon xoa
						System.out.println(deleteEmployee);
						
						// comfirm tu nguoi dung
						System.out.println("Xac nhan xoa nhan vien? (Y/N)");
						String confirm = "";
						int cntYNInvalid = 0;
						boolean isCancelled = false;
						while(true) {
							
							confirm = sc.nextLine();
							
							if(confirm.equalsIgnoreCase("Y") || confirm.equalsIgnoreCase("N")) {
								break;
							}
							
							cntYNInvalid++;
							
							if(cntYNInvalid >= 3) {
								System.out.println("Ban da nhap sai qua nhieu lan. Thao tac bi HUY!");
								isCancelled = true;
								break;
							}
							
							System.out.println("Khong hop le. Vui long nhan lai! (Y/N)");
							
						}
						
						if(isCancelled) break;
						
						
						if(confirm.equalsIgnoreCase("Y")) {
							manager.deleteEmployee(id);
							System.out.println("XOA NHAN VIEN THANH CONG!");
						}
						else {
							System.out.println("Thao tac bi huy bo!");
						}
						
					}
					catch(EmployeeNotFoundException e){
						System.out.println(e.getMessage());
					}
					break;
				}
				case 5:
				{
					List<Employee> sortedList = manager.getTopHighestSalaryEmployees();
					
					if(sortedList.isEmpty()) {
						System.out.println("DANH SACH NHAN VIEN TRONG!");
						break;
					}
					
					System.out.println("DANH SACH NHAN VIEN CO LUONG CAO NHAT");
					for(Employee e : sortedList) {
						System.out.println(e);
					}
					
					break;
				}
				case 6: {
					System.out.println("Ban co muon thoat chuong trinh?");
					end = false;
					System.out.println("Thoat chuong trinh thanh cong!");
					break;
				}
			}
		}
	}
	
	public static int inputEmployeeType(Scanner scanner) {
		while(true) {
			try {
				int option = Integer.parseInt(scanner.nextLine());
				int _MAX = 2, _MIN = 1;
				InputChoiceValidator.validateChoice(option, _MIN, _MAX);
				
				return option;
			}
			catch(NumberFormatException e1) {
				System.out.println("Du lieu khong hop le. Ban phai nhap so, vui long nhap lai!");
			}
			catch(IllegalArgumentException e2) {
				System.out.println(e2.getMessage() + ", vui long nhap lai!");
			}
		}
	}
	
	public static Employee inputEmployeeInformation(int option, Scanner scanner) {
		
		Employee newEmployee = null;
		
		// cần thêm kiểm tra id khác rỗng
		System.out.println("Nhap id: ");
		String id = scanner.nextLine();
		
		// name khác rỗng
		System.out.println("Nhap name: ");
		String name = scanner.nextLine();
		
		System.out.println("Nhap salary: ");
		double salary = Main.inputAndValidateSalary(scanner); // de the hien ro day la method cua Main, hoac khong can bo static cung duoc
		
		System.out.println("Nhap email");
		String email = Main.inputAndValidationEmail(scanner);
		
		// cần thêm xử lý dùng regex để kiểm nhập vào đúng độ dài, chỉ toàn là chữ số, bắt đầu bằng 0
		System.out.println("Nhap phone number: ");
		String phoneNumber = scanner.nextLine();
		
		// Full-time
		if(option == 1) {
			System.out.println("Nhap bonus: ");
			double bonus = Main.inputAndValidateBonus(scanner);
			
			newEmployee = new FullTimeEmployee(id, name, salary, email, phoneNumber, bonus);
		}
		//Part-time
		if(option == 2) {
			System.out.println("Nhap vao working hours: ");
			double workingHours = Main.inputAndValidateWorkingHours(scanner);
			
			System.out.println("Nhap vao rate: ");
			double rate = Main.inputAndValidateRate(scanner);
			
			newEmployee = new PartTimeEmployee(id, name, salary, email, phoneNumber, workingHours, rate);
		}
		
		return newEmployee;
	}
	
	public static boolean handleDuplicateEmployee(Employee newEmployee, EmployeeManager manager, Scanner scanner) {
		
		int countIdInvalid = 0, countEmailInvalid = 0;
		while(true) {
			
			if(countIdInvalid >= 3 || countEmailInvalid >= 3 ) {
				System.out.println("Ban da nhap sai qua nhieu lan. Vui long kiem tra lai thong tin nhan vien!. Thao tac nhap da bi huy!");
				return false;
			}
			
			try {
				// add vao danh sach
				manager.addEmployee(newEmployee);
				return true;
			}
			catch(DuplicateIdException e1) {
				countIdInvalid++;
				System.out.println(e1.getMessage());
				System.out.println("Vui long nhap lai!");				
				System.out.println("Nhap Id: ");
				String newId = scanner.nextLine();
				newEmployee.setId(newId);
			}
			catch(DuplicateEmailException e2) {
				countEmailInvalid++;
				System.out.println(e2.getMessage());
				System.out.println("Vui long nhap lai!");
				System.out.println("Nhap email: ");
				String newEmail = Main.inputAndValidationEmail(scanner);
				newEmployee.setEmail(newEmail);
			}
			catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static double inputAndValidateSalary(Scanner scanner) {
		while(true) {
			try {
				double salary = Double.parseDouble(scanner.nextLine());
				
				// kiem tra logic
				
				if(salary < 0) {
					throw new IllegalArgumentException("Salary phai >= 0.");
				}
				
				return salary;
			}
			catch(NumberFormatException e1) {
				System.out.println("Du lieu khong hop le. Ban phai nhap vao so thuc!");
			}
			catch(IllegalArgumentException e2) {
				System.out.println(e2.getMessage());
			}
		}
	}
	
	public static String inputAndValidationEmail(Scanner scanner) {
		//	\\. : bat buoc co dau cham, {2,} phai co tu 2 ki tu tro len com, net,org, vn,....
		final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
		
		final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
		
		while(true) {
			try {
				String email = scanner.nextLine();
				// kiem tra email
				// CACH TOI UU HON DUNG PATTERN
				if(!EMAIL_PATTERN.matcher(email).matches()) {
					throw new IllegalArgumentException("Email khong hop le. Vui long nhap lai!");
				}
				
				return email;
			}
			catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static double inputAndValidateBonus(Scanner scanner) {
		while(true) {
			try {
				double bonus = Double.parseDouble(scanner.nextLine());
				
				if(bonus < 0) {
					throw new IllegalArgumentException("Bonus phai >= 0. Vui long nhap lai!");
				}
				
				return bonus;
			}
			catch(NumberFormatException e1) {
				System.out.println("Du lieu khong hop le. Phai nhap so thuc!");
			}
			catch(IllegalArgumentException e2) {
				System.out.println(e2.getMessage());
			}
		}
	}
	
	public static double inputAndValidateWorkingHours(Scanner scanner) {
		while(true) {
			try {
				double workingHours = Double.parseDouble(scanner.nextLine());
			
				if(workingHours < 0) {
					throw new IllegalArgumentException("workingHours phai >= 0. Vui long nhap lai!");
				}
				
				return workingHours;
			}
			catch(NumberFormatException e1) {
				System.out.println("Du lieu khong hop le. Ban phai nhap so nguyen, vui long nhap lai!");
			}
			catch(IllegalArgumentException e2) {
				System.out.println(e2.getMessage() + ", vui long nhap lai!");
			}
		}
	}
	
	public static double inputAndValidateRate(Scanner scanner) {
		while(true) {
			try {
				double rate = Integer.parseInt(scanner.nextLine());
				
				if(rate < 0) {
					throw new IllegalArgumentException("workingHours phai >= 0. Vui long nhap lai!");
				}
				
				return rate;
			}
			catch(NumberFormatException e1) {
				System.out.println("Du lieu khong hop le. Ban phai nhap so nguyen, vui long nhap lai!");
			}
			catch(IllegalArgumentException e2) {
				System.out.println(e2.getMessage() + ", vui long nhap lai!");
			}
		}
	}
}
