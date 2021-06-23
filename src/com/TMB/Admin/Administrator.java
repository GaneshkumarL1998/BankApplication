package com.TMB.Admin;


import java.util.Scanner;
import com.TMB.Employee.Employee;
import com.TMB.Employee.EmployeeDB;
import com.TMB.datafiles.AccountDataClass;

public class Administrator implements AdminInterface{
	private String userid;
	private String password;
	private int empid;
	private String name;
	private long mobileno;
	private String DOB;
	private String emailid;
	private double salary;
	//Constructors....
	public Administrator(String userid, String password, int empid, String name, long mobileno, String DOB,
			String emailid,double salary) {
		super();
		this.userid = userid;
		this.password = password;
		this.empid = empid;
		this.name = name;
		this.mobileno = mobileno;
		this.DOB = DOB;
		this.emailid = emailid;
		this.salary=salary;
	}
	public Administrator() {
		super();
	}
	
	//Getters and setters...
	public String getUserid() {
		return userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobileno() {
		return mobileno;
	}
	public void setMobileno(long mobileno) {
		this.mobileno = mobileno;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String DOB) {
		this.DOB = DOB;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String toString() {
		return "[userid=" + userid + ", password=" + password + ", empid=" + empid + ", name=" + name
				+ ", mobileno=" + mobileno + ", DOB=" + DOB + ", emailid=" + emailid + ", salary=" + salary + "]";
	}
	
	public AdminInterface login()
	{
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your user id");
		String e_userid=sc.nextLine();
		System.out.println("Enter your Current Password");
		String e_password=sc.nextLine();
		Administrator ad=admindb.selectAdmin(e_userid);
		if(ad!=null)
		{
			if(ad.getPassword().equals(e_password))
			{
				return ad;
			}
			else
			{
				System.out.println("Sorry your password is wrong...");
			}
		}
		else 
		{
			System.out.println("Sorry this userid is invalid...");
		}
		return null;
	}
	public void adduser()
	{	
		int employeeid=AccountDataClass.empid++;
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the employee name");
		String acc_name=sc.nextLine();
		String acc_DOB="";
		while(true)
		{
			System.out.println("Enter his DOB in dd/MM/yyyy format");
			acc_DOB=sc.nextLine().replaceAll("[_ -/:;]", "/");
			int day=Integer.parseInt(acc_DOB.substring(0, acc_DOB.indexOf("/")));
			int month=Integer.parseInt(acc_DOB.substring(acc_DOB.indexOf("/")+1, acc_DOB.lastIndexOf("/")));
			int year=Integer.parseInt(acc_DOB.substring(acc_DOB.lastIndexOf("/")+1));
			if(isValidDate(day, month, year))
				break;
			else
				System.out.println("Sorry date is not valid please check date");
		}
		System.out.println("Enter the account holder mobile numer");
		long acc_mobile;
		while(true)
		{
			acc_mobile=sc.nextLong();
			sc.nextLine();
			if(String.valueOf(acc_mobile).length()==10)
				break;
			else
				System.out.println("Sorry mobile number must be in 10 digits.Re-enter mobileno");
		}
		String acc_email="";
		while(acc_email.contains("@")!=true)
		{
			System.out.println("Enter the employee email id");
			acc_email=sc.nextLine();
			if(acc_email.contains("@")==false)
				System.out.println("mail id must contails @ operator");
		}
		
		System.out.println("Enter the employee salary");
		double sal=sc.nextDouble();
		sc.nextLine();
		String employeeuserid=acc_email.substring(0,acc_email.lastIndexOf('@'));
		String employeepassword=acc_DOB.substring(0,6);
		Employee emp=new Employee(employeeuserid,employeepassword,employeeid,acc_name,acc_mobile,acc_DOB,acc_email,sal);
		boolean isinsert=EmployeeDB.insertelement(emp);
		if(isinsert==true)
		{
			System.out.println("Employee added successfully...His Details is");
			System.out.println("UserId         Password       EmployeeId     EmployeeName       DateOfBirth    MobileNumber   Salary    emailid ");
			emp.printEmployee();
		}
		else
		{
			System.out.println("Sorry data is not inserted.. please check whether you enter your own phoneno,emailid...");
		}
	}
	public boolean isLeap(int year){
		return(((year%4==0)&&(year%100!=0))||(year%400==0));
	}
	public boolean isValidDate(int day,int month,int year)
	{
		int maxyear=2021;int minyear=1900;
		if((year>maxyear)||(year<minyear))
			return false;
		if(month<1 ||month>12)
			return false;
		if(day<1 ||day>31)
			return false;
		if(month==2)
		{
			if(isLeap(year))
				return(day<=29);
			else
				return (day<=28);
		}
		if(month==4 ||month==6 ||month==9 ||month==11)
			return (day<=30);
		return true;
	}
	public void removeuser()
	{
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the id to remove");
		int employeeid=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the his name");
		String acc_name=sc.nextLine();
		Employee emp=EmployeeDB.selectEmployee(employeeid);
		if(emp!=null)
		{
			if(emp.getName().equalsIgnoreCase(acc_name))
			{
				EmployeeDB.removeEmployee(employeeid);
			}
			else
			{
				System.out.println("Sorry employeeid and name are mismatched..");
			}
		}
		else
		{
			System.out.println("Sorry this account number is not available...");
		}
	}
	public void updateuser()
	{
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the employeeid to update");
		int employeeid=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the employee name");
		String acc_name=sc.nextLine();
		Employee cs=EmployeeDB.selectEmployee(employeeid);
			if(cs!=null)
			{
				if( cs.getName().equalsIgnoreCase(acc_name))
				{
					System.out.println("Enter 1.name update 2.mobile number update 3.emailid update 4.password update 5.update salary");
					int choice=sc.nextInt();sc.nextLine();
					switch(choice)
					{
					case 1:System.out.println("Enter your new update in name");
							String newname=sc.nextLine();
							cs.setName(newname);
							EmployeeDB.updateEmployee(cs);
							break;
					case 2:long acc_mobile;
							while(true)
							{System.out.println("Enter the employee's new mobile no");
								acc_mobile=sc.nextLong();
								sc.nextLine();
								if(String.valueOf(acc_mobile).length()==10)
									break;
								else
									System.out.println("Sorry mobile number must be in 10 digits.Re-enter mobileno");
							}
							cs.setMobileno(acc_mobile);EmployeeDB.updateEmployee(cs);
							break;
					case 3:String acc_email="";
							while(acc_email.contains("@")!=true)
							{
								System.out.println("Enter the employee email id");
								acc_email=sc.nextLine();
								if(acc_email.contains("@")==false)
									System.out.println("mail id must contails @ operator");
							}
							cs.setEmailid(acc_email);EmployeeDB.updateEmployee(cs);
							break;
					case 4:System.out.println("Enter your new password");
							String newpass=sc.nextLine();
							cs.setPassword(newpass);EmployeeDB.updateEmployee(cs);
							break;
					case 5:System.out.println("Enter the new salary");
							double salary=sc.nextDouble();sc.nextLine();
							cs.setSalary(salary);EmployeeDB.updateEmployee(cs);
							break;
					default :System.out.println("Invalid input");
					}
				}
				else
				{
					System.out.println("account holder name is wrong");
				}
			}
			else
			{
				System.out.println("Sorry employee id is not available");
			}
	}
	public void viewAllUsers()
	{
		System.out.println("UserId         Password       EmployeeId     EmployeeName       DateOfBirth    MobileNumber   Salary    emailid ");
		EmployeeDB.selectAllEmployee();
	}
	public void searchusers()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Search Employee by 1.Employee id 2.name 3.mobileno 4.emailid");
		int num=sc.nextInt();
		sc.nextLine();
		switch(num)
		{
		case 1:System.out.println("Enter the employee id");int e_id=sc.nextInt();sc.nextLine();
				System.out.println("UserId         Password       EmployeeId     EmployeeName       DateOfBirth    MobileNumber   Salary    emailid ");		
				EmployeeDB.searchEmployee(e_id);;break;
		case 2:System.out.println("Enter the employee name");String e_name=sc.nextLine();
				System.out.println("UserId         Password       EmployeeId     EmployeeName       DateOfBirth    MobileNumber   Salary    emailid ");
				EmployeeDB.searchEmployee("e_name",e_name);;break;
		case 3:System.out.println("Enter the employee mobile no");long mobileno=sc.nextLong();sc.nextLine();
				System.out.println("UserId         Password       EmployeeId     EmployeeName       DateOfBirth    MobileNumber   Salary    emailid ");
				EmployeeDB.searchEmployee(mobileno);;break;
		case 4:System.out.println("Enter the employee emailid");String emailid=sc.nextLine();
				System.out.println("UserId         Password       EmployeeId     EmployeeName       DateOfBirth    MobileNumber   Salary    emailid ");
				EmployeeDB.searchEmployee("emailid",emailid);;break;
		default:System.out.println("Invalid input");
		}
	}
	public void updateAdminDetails()
	{
		Administrator cs=admindb.selectAdmin(this.getUserid());
		Scanner sc=new Scanner(System.in);		
		System.out.println("Enter 1.name update 2.mobile number update 3.emailid update 4.password update 5.update salary");
		int choice=sc.nextInt();sc.nextLine();
		switch(choice)
		{
		case 1:System.out.println("Enter your new update in name");
				String newname=sc.nextLine();
				cs.setName(newname);
				admindb.updateAdmin(cs);
				break;
		case 2:long acc_mobile;
				while(true)
				{System.out.println("Enter the admin's new mobile no");
					acc_mobile=sc.nextLong();
					sc.nextLine();
					if(String.valueOf(acc_mobile).length()==10)
						break;
					else
						System.out.println("Sorry mobile number must be in 10 digits.Re-enter mobileno");
				}
				cs.setMobileno(acc_mobile);admindb.updateAdmin(cs);
				break;
		case 3:String acc_email="";
				while(acc_email.contains("@")!=true)
				{
					System.out.println("Enter the admin's email id");
					acc_email=sc.nextLine();
					if(acc_email.contains("@")==false)
						System.out.println("mail id must contails @ operator");
				}
				cs.setEmailid(acc_email);admindb.updateAdmin(cs);
				break;
		case 4:System.out.println("Enter your new password");
				String newpass=sc.nextLine();
				cs.setPassword(newpass);admindb.updateAdmin(cs);
				break;
		case 5:System.out.println("Enter the new salary");
				double salary=sc.nextDouble();sc.nextLine();
				cs.setSalary(salary);admindb.updateAdmin(cs);
				break;
		default :System.out.println("Invalid input");
		}
	}
	public void printAdminDetails()
	{
		Administrator ad=admindb.selectAdmin(this.getUserid());
		System.out.println("UserId         Password       EmployeeId     EmployeeName       DateOfBirth    MobileNumber   Salary    emailid ");
		System.out.print(userid);
		for(int i=userid.length();i<15;i++)
			System.out.print(" ");
		System.out.print(password);
		for(int i=password.length();i<15;i++)
			System.out.print(" ");
		System.out.print(empid);
		String str=String.valueOf(empid);
		for(int i=str.length();i<15;i++)
			System.out.print(" ");
		System.out.print(name);
		for(int i=name.length();i<19;i++)
			System.out.print(" ");
		System.out.print(DOB);
		for(int i=DOB.length();i<15;i++)
			System.out.print(" ");
		System.out.print(mobileno);
		str=String.valueOf(mobileno);
		for(int i=str.length();i<15;i++)
			System.out.print(" ");
		System.out.print(salary);
		str=String.valueOf(salary);
		for(int i=str.length();i<10;i++)
			System.out.print(" ");
		System.out.print(emailid);
		System.out.println();
	}
	
	
}
