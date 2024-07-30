package week1pgms;
import java.util.*;
public class EmployeeManagemet {
    public static void main(String args[])
    {
    	Scanner sc = new Scanner(System.in);
    	EmployeeManager eobj = new EmployeeManager();
    	int k=0;
    	do
    	{
    		System.out.println("1.Add 2.Search 3.Traverse 4.Delete 5.Exit");
    		System.out.println("Choose an option:");
    		 k = sc.nextInt();
    		switch(k)
    		{
    		case 1: System.out.println("Enter id,name,position,salary");
      	              int id=sc.nextInt();
      	             sc.nextLine();
      	             String empname=sc.nextLine();
      	             String emp_position=sc.nextLine();
      	             double sal = sc.nextDouble();
               	     Employee emp = new Employee(id,empname,emp_position,sal);
      	             eobj.addEmployee(emp);
      	             break;
    		case 2: System.out.println("Enter id you want to search:");
    		        int searchid=sc.nextInt();
    		        eobj.searchEmployee(searchid);
    		        break;
    		case 3: eobj.traverseEmployees();
    		         break;
    		case 4: System.out.println("Enter id do you want to delete:");
    		        int delid=sc.nextInt();
    		        eobj.deleteEmployee(delid);
    		        break;
    		case 5: System.out.println("Exiting..");
    			break;
    		default: System.out.println("Invalid choice");
    		}
    	}while(k!=5);
    	
    	
    }
}


class Employee {
    private int employeeId;
    private String name;
    private String position;
    private double salary;

   
    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }
}

class EmployeeManager {
    private ArrayList<Employee> employees;

    public EmployeeManager() {
        employees = new ArrayList<>();
    }
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
    
    public void searchEmployee(int employeeId) {
        for (Employee emp : employees) {
            if (emp.getEmployeeId() == employeeId) {
               System.out.println("Employee found");
               System.out.println(emp.toString());
               return;
            }
        }
        System.out.println("No id found");
    }
    public void traverseEmployees() {
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }

    public void deleteEmployee(int employeeId) {
        for (Employee emp : employees) {
            if (emp.getEmployeeId() == employeeId) {
                employees.remove(emp);
               System.out.println("Deleted Successfully");
               return ;
            }
        }
        System.out.println("Id not found to delete");
        return;
    }
}
