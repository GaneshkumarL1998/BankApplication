package com.TMB.Admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.TMB.Employee.Employee;
import com.TMB.Employee.EmployeeInterface;
import com.TMB.datafiles.AccountDataClass;
import com.TMB.datafiles.DataClass;

public class Administrator implements AdminInterface, Serializable{
	private static final long serialVersionUID=3L;
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
	private void writeObject(ObjectOutputStream oos)throws Exception
	{
		oos.defaultWriteObject();
		String epass="11/1999/"+password;
		oos.writeObject(epass);
	}
	private void readObject(ObjectInputStream ois)throws Exception
	{
		ois.defaultReadObject();
		password=((String)ois.readObject()).substring(8);
	}
	
	public AdminInterface login()
	{
		boolean check=true;
		Administrator ad=null;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your user id");
		String e_userid=sc.nextLine();
		System.out.println("Enter your Current Password");
		String e_password=sc.nextLine();
		ArrayList<Administrator> list=DataClass.admin;
		{
			for(Object ob:list)
			{
				Administrator cs=(Administrator)ob;
				if(e_userid.equals(cs.getUserid()))
				{	
					check=false;
					if(e_password.equals(cs.getPassword()))
					{	
						
						System.out.println("Successfully loggedin...");
						ad=cs;break;
					}
					else
					{
						System.out.println("Sorry your password is incorrect...");
						break;
					}
				}
					
			}
			//list=(ArrayList)ois.readObject();
		}
		if(check==true)
			System.out.println("Sorry this Admin id is wrong..");
		return ad;
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
		ArrayList<Employee> emplist=DataClass.employee;
		//String userid, String password, int empid, String name, long mobileno, String DOB, String emailid,double salary
		emplist.add(new Employee(employeeuserid,employeepassword,employeeid,acc_name,acc_mobile,acc_DOB,acc_email,sal));
		System.out.println("Employee added successfully...His Details is");
		System.out.println("UserId         Password       EmployeeId     EmployeeName       DateOfBirth    MobileNumber   Salary    emailid ");
		emplist.get(emplist.size()-1).printEmployee();
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
		ArrayList<Employee> list=DataClass.employee;
		for(int i=0;i<list.size();i++)
		{
			Employee cs=(Employee)list.get(i);
			if(cs.getEmpid()==employeeid)
			{
				if( cs.getName().equalsIgnoreCase(acc_name))
				{
					list.remove(i);
					System.out.println("Successfully removed....");check=false;break;
				}
				else
				{
					System.out.println("account holder name is wrong");check=false;break;
				}
			}
		}
		if(check==true)
			System.out.println("this details is not available");
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
		ArrayList<Employee> list=DataClass.employee;
		for(int i=0;i<list.size();i++)
		{
			Employee cs=(Employee)list.get(i);
			if(cs.getEmpid()==employeeid)
			{
				if( cs.getName().equalsIgnoreCase(acc_name))
				{
					System.out.println("Enter 1.name update 2.mobile number update 3.emailid update 4.password update 5.update salary");
					int choice=sc.nextInt();sc.nextLine();
					switch(choice)
					{
					case 1:System.out.println("Enter your new update in name");
							String newname=sc.nextLine();
							list.get(i).setName(newname);
							System.out.println("Updated successfully");check=false;break;
					case 2:long acc_mobile;
							while(true)
							{
								acc_mobile=sc.nextLong();
								sc.nextLine();
								if(String.valueOf(acc_mobile).length()==10)
									break;
								else
									System.out.println("Sorry mobile number must be in 10 digits.Re-enter mobileno");
							}
							list.get(i).setMobileno(acc_mobile);
							System.out.println("Updated successfully");check=false;break;
					case 3:String acc_email="";
							while(acc_email.contains("@")!=true)
							{
								System.out.println("Enter the employee email id");
								acc_email=sc.nextLine();
								if(acc_email.contains("@")==false)
									System.out.println("mail id must contails @ operator");
							}
							list.get(i).setEmailid(acc_email);
							System.out.println("Updated successfully");check=false;break;
					case 4:System.out.println("Enter your new password");
							String newpass=sc.nextLine();
							list.get(i).setPassword(newpass);
							System.out.println("Updated successfully");check=false;break;
					case 5:System.out.println("Enter the new salary");
							double salary=sc.nextDouble();sc.nextLine();
							list.get(i).setSalary(salary);
							System.out.println("Salary updated successfully...New Salary =Rs"+salary);check=false;break;
					default :System.out.println("Invalid input");check=false;
					}
				}
				else
				{
					System.out.println("account holder name is wrong");check=false;break;
				}
			}
		}
		if(check==true)
			System.out.println("this details is not available");
	}
	public void viewAllUsers()
	{
		ArrayList<Employee> list=DataClass.employee;
		if(list.size()==0)
			System.out.println("Sorry you dont have any customers....");
		else 
		{
			System.out.println("UserId         Password       EmployeeId     EmployeeName       DateOfBirth    MobileNumber   Salary    emailid ");
			for(int i=0;i<list.size();i++)
			{
				Employee cs=(Employee)list.get(i);
				cs.printEmployee();
			}
		}
	}
	public void searchusers()
	{
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		System.out.println("Search Employee by 1.Employee id 2.name 3.mobileno 4.emailid");
		int num=sc.nextInt();
		sc.nextLine();
		ArrayList<Employee> list=DataClass.employee;
		switch(num)
		{
		case 1:searchbyaccno(list);break;
		case 2:searchbyname(list);break;
		case 3:searchbymobile(list);break;
		case 4:searchbyemailid(list);break;
		default:System.out.println("Invalid input");
		}
	}
	public void changePassword()
	{
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		String e_userid=this.getUserid();
		System.out.println("Enter your Current Password");
		String e_password=sc.nextLine();
		ArrayList<Administrator> list=DataClass.admin;
		//while(list==null)
		{
			for(Object ob:list)
			{
				Administrator cs=(Administrator)ob;
				if(e_userid.equals(cs.getUserid()))
				{	
					check=false;
					if(e_password.equals(cs.getPassword()))
					{
						System.out.println("Enter your new password");
						String password1=sc.nextLine();
						System.out.println("Re-enter your password");
						String password2=sc.nextLine();
						if(password1.equals(password2))
						{
							cs.setPassword(password1);
							System.out.println("Successfully changed...Your new password ="+password1);
						}
						else
							System.out.println("Sorry your new passwords are not matched...both are different");
						break;
						
					}
					else
					{
						System.out.println("Sorry your password is incorrect...");
						break;
					}
				}
					
			}
			//list=(ArrayList)ois.readObject();
		}
		if(check==true)
			System.out.println("Sorry this employee id is wrong..");
	}
	public void searchbyname(ArrayList list)
	{
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Employee name");
		String name=sc.nextLine();
		for(int i=0;i<list.size();i++)
		{
			Employee cs=(Employee)list.get(i);
			if(cs.getName().equalsIgnoreCase(name))
			{		
				System.out.println("UserId         Password       EmployeeId     EmployeeName       DateOfBirth    MobileNumber   Salary    emailid ");
				cs.printEmployee();
				check=false;break;
			}
		}
		if(check==true)
			System.out.println("this details is not available");
	}
	public void searchbyaccno(ArrayList list)
	{
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Employee id");
		int employeeid=sc.nextInt();sc.nextLine();
		for(int i=0;i<list.size();i++)
		{
			Employee cs=(Employee)list.get(i);
			if(cs.getEmpid()==employeeid)
			{
				System.out.println("UserId         Password       EmployeeId     EmployeeName       DateOfBirth    MobileNumber   Salary    emailid ");
				cs.printEmployee();
				check=false;break;
			}
		}
		if(check==true)
			System.out.println("this details is not available");
	}
	public void searchbymobile(ArrayList list)
	{
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the employee mobile numer");
		long acc_mobile=sc.nextLong();sc.nextLine();
		for(int i=0;i<list.size();i++)
		{
			Employee cs=(Employee)list.get(i);
			if(cs.getMobileno()==acc_mobile)
			{
				System.out.println("UserId         Password       EmployeeId     EmployeeName       DateOfBirth    MobileNumber   Salary    emailid ");
				cs.printEmployee();
				check=false;break;
			}
		}
		if(check==true)
			System.out.println("this details is not available");
	}
	public void searchbyemailid(ArrayList list)
	{
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the employee mailid");
		String acc_email=sc.nextLine();
		for(int i=0;i<list.size();i++)
		{
			Employee cs=(Employee)list.get(i);
			if(cs.getEmailid().equalsIgnoreCase(acc_email))
			{
				System.out.println("UserId         Password       EmployeeId     EmployeeName       DateOfBirth    MobileNumber   Salary    emailid ");
				cs.printEmployee();
				check=false;break;
			}
		}
		if(check==true)
			System.out.println("this details is not available");
	}
	
	
}
