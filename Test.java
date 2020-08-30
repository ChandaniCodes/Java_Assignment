
import java.util.Scanner;

class Admin{
	
	String admin_name;
	
	public Admin(String admin_name){

		this.admin_name = admin_name;
	}

	public void showAdminData(){
		System.out.println(admin_name + " is admin");
	}

	public void updateAdminData(String admin_name){
		this.admin_name = admin_name;
		System.out.println("Admin Details Updated.");
	}

}

class Department{
	
	String dept_name;
	Admin admin;
	Rules rules;
	Employee emp_obj;	

	Department(String dept_name,Admin admin){

		this.dept_name = dept_name;
		this.admin = admin;
	}

	public void showDepartmenteDetails(){

		System.out.println(dept_name + " Department");
	}

	public void imposeRule(Rules rules){

		this.rules = rules;
		System.out.println(rules.rule_name + " rule is imposed on the department by " + admin.admin_name);
	}

	public void deleteDepartment(Employee emp_obj){

		this.emp_obj = emp_obj;

		if (emp_obj.no_of_emp > 0) 
			System.out.println("You can`t delete department.");
		else
			System.out.println("Department Deleted");				
	}

	public void updateDepartment(String dept_name){
		this.dept_name = dept_name;
		System.out.println("Department detail updated.");
	}

}

class Employee{

	String emp_name;
	static byte no_of_emp;
	Department dept;

	public Employee(String emp_name, Department dept){
		
		this.emp_name = emp_name;
		this.dept = dept;
		no_of_emp++;
	}

	public void showEmpDetails(Employee []empobj){

		for(Employee emp:empobj){
				if(emp == null)
					continue;
				System.out.println("Employee Name: " + emp.emp_name);
		}		
	}

	public void updateEmployee(String updateEmpName,Employee []empObj,String newName){

		boolean flag = false;
		for (Employee emp:empObj) {

			if (updateEmpName.equalsIgnoreCase(emp.emp_name)) 
				this.emp_name = newName;
				flag = true;
		}
		if (flag) 
			System.out.println("Employee details updated.");
		else
			System.out.println("Invalid Employee can`t update.");
	}

	public void deleteEmployee(String empname, Employee []empObj){

		boolean flag = false;

		for (byte i=0; i<10 ;i++) {
			if (empname.equalsIgnoreCase(empObj[i].emp_name)) {
				empObj[i] = null;
				flag = true;
			}
		}

		if (flag) 
			System.out.println("Employee deleted");
		else
			System.out.println("Can`t delete bcz this employee is not working here.");
	}
}


class Rules{

	String rule_name;
	Admin admin;

	public Rules(Admin admin,String rule_name){

		this.rule_name = rule_name;
		this.admin = admin;
	}

	public void showRules(){
		System.out.println(rule_name + " rule is created by " + admin.admin_name);
	}

}

public class Test{

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		Admin admin = new Admin("Chandani");
		
		Rules rule1 = new Rules(admin,"Meet deadline");
		Rules rule2 = new Rules(admin,"No phone allowed");

		Department FE = new Department("FrontEnd",admin);
		Department BE = new Department("BackEnd",admin);

		FE.imposeRule(rule1);
		BE.imposeRule(rule2);

		Employee empArr[] = new Employee[10];

		String empName = "", empDept = "";

		for (byte i = 0; i<10; i++ ) {

			System.out.println("Enter employee name");
			empName = scan.nextLine();
			System.out.println("Enter employee department (FE or BE)");
			empDept = scan.nextLine();

			if (empDept.equalsIgnoreCase("FE")) 
				empArr[i] = new Employee(empName,FE);
			else
				empArr[i] = new Employee(empName,BE);
			
		}

		System.out.println("Select your options");
		System.out.println("1. Read admin details");
		System.out.println("2 .Update admin details");

		System.out.println("3. Read department details");
		System.out.println("4 .Update front end department details");
		System.out.println("5 .Update back end department details");
		System.out.println("6. Delete department details");

		System.out.println("7 .Read employee details");
		System.out.println("8 .Update employee details");
		System.out.println("9 .Delete employee details");

		System.out.println("10 .Read rules");
		
		String ch = "y";

		do{
			System.out.println("Enter your choice :");
			int choice = scan.nextInt();

			switch(choice){
				case 1: admin.showAdminData();
						break;

				case 2: System.out.println("Enter new admin name");
						scan.nextLine();
						String adminName = scan.nextLine();
						admin.updateAdminData(adminName);
						break;

				case 3: FE.showDepartmenteDetails();
						BE.showDepartmenteDetails();
						break;

				case 4: System.out.println("Enter new department name");
						scan.nextLine();
						String updateFE = scan.nextLine();
						FE.updateDepartment(updateFE);
						break;

				case 5: System.out.println("Enter new department name");
						scan.nextLine();
						String updateBE = scan.nextLine();
						BE.updateDepartment(updateBE);
						break;

				case 6: FE.deleteDepartment(empArr[0]);
						break;

				case 7: empArr[0].showEmpDetails(empArr);
						break;

				case 8: System.out.println("Enter employee name which you want to update.");
						scan.nextLine();
						String updateEmpName = scan.nextLine();

						System.out.println("Enter new employee name");
						String newName = scan.nextLine();

						empArr[0].updateEmployee(updateEmpName,empArr,newName);
						break;

				case 9: System.out.println("Enter employee name Which you want to delete.");
						scan.nextLine();
						String deleteEmpName = scan.nextLine(); 
						empArr[0].deleteEmployee(deleteEmpName,empArr);
						break;

				case 10: rule1.showRules();
						 rule2.showRules();
						 break;

				default: System.out.println("Invalid choice :(");
			}
			
			System.out.println("Do you want to continue ? (y/n)");
			ch = scan.next();

		}while(!ch.equals("n"));

	}
}