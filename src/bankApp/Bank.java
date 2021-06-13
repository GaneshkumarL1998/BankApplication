package bankApp;

import java.io.Serializable;

import com.TMB.Admin.AdminInterface;
import com.TMB.Admin.Administrator;
import com.TMB.Customer.Customer;
import com.TMB.Customer.CustomerInterface;
import com.TMB.Employee.Employee;
import com.TMB.Employee.EmployeeInterface;

public class Bank implements Serializable{
	private static EmployeeInterface ei;
	private static AdminInterface ai;
	private static CustomerInterface ci;
	public static void setEmployee(int num)
	{
		if(num==1)
			ai=new Administrator();
		else if(num==2)
			ei=new Employee();
		else if(num==3)
			ci=new Customer();	
	}
	public static EmployeeInterface getEmployeeInterface()
	{
		return ei;
	}
	public static CustomerInterface getCustomerInterface()
	{
		return ci;
	}
	public static void setEmployeeInterface(EmployeeInterface emp)
	{
		ei=emp;
	}
	public static void setCustomerInterface(CustomerInterface cust)
	{
		ci=cust;
	}
	public static AdminInterface getAdminInterface() {
		return ai;
	}
	public static void setAdminInterface(AdminInterface admin) {
		ai = admin;
	}
	
}
