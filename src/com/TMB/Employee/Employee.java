package com.TMB.Employee;

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
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import com.TMB.Account.Account;
import com.TMB.Account.BikeLoan;
import com.TMB.Account.CarLoan;
import com.TMB.Account.HomeLoan;
import com.TMB.Account.Loan;
import com.TMB.Account.LoanAccount;
import com.TMB.Account.LoanFactory;
import com.TMB.Account.PersonalLoan;
import com.TMB.Customer.Customer;
import com.TMB.datafiles.AccountDataClass;
import com.TMB.datafiles.DataClass;

public class Employee implements EmployeeInterface, Serializable{
	private final static long serialVersionUID=2L;
	private String userid;
	private String password;
	private int empid;
	private String name;
	private long mobileno;
	private String DOB;
	private String emailid;
	private double salary;
	public Employee(String userid, String password, int empid, String name, long mobileno, String DOB, String emailid,
			double salary) {
		super();
		this.userid = userid;
		this.password = password;
		this.empid = empid;
		this.name = name;
		this.mobileno = mobileno;
		this.DOB=DOB;
		this.emailid = emailid;
		this.salary = salary;
	}
	public Employee() {
		super();
	}
	
	private void writeObject(ObjectOutputStream oos)throws Exception
	{
		oos.defaultWriteObject();
		String epass="rama"+password;
		oos.writeObject(epass);
	}
	private void readObject(ObjectInputStream ois)throws Exception
	{
		ois.defaultReadObject();
		password=((String)ois.readObject()).substring(4);
	}
	
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
	public void printEmployee(){
		//15,15,15,20,15,15,10
		//System.out.println("UserId         Password       EmployeeId     EmployeeName       DateOfBirth    MobileNumber   Salary    emailid ");
		
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
	
	public EmployeeInterface login()
	{
		ArrayList<Employee> list=DataClass.employee;
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your user id");
		String e_userid=sc.nextLine();
		System.out.println("Enter your Current Password");
		String e_password=sc.nextLine();
		Employee ad=null;
		for(Object ob:list)
		{
			Employee cs=(Employee)ob;
			if(e_userid.equals(cs.getUserid()))
			{	
				check=false;
				if(e_password.equals(cs.getPassword()))
				{	
					
					System.out.println("Successfully loggedin...");ad=cs;
					break;
				}
				else
				{
					System.out.println("Sorry your password is incorrect...");
					break;
				}
			}
				
		}
		if(check==true)
			System.out.println("Sorry this employee id is wrong..");
		return ad;
	}
	public void adduser()
	{	
		long accno=AccountDataClass.Savings_Acc_no++;
		String custid=""+AccountDataClass.customerid;
		AccountDataClass.customerid++;
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Account holder name");
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
		while(acc_email.contains("@")==false)
		{
			System.out.println("Enter the employee email id");
			acc_email=sc.nextLine();
			if(acc_email.contains("@")==false)
				System.out.println("mail id must contails @ operator");
		}
		double accbal=0;
		while(accbal<1000)
		{
			System.out.println("Enter your initial deposit amount greater than or equalto 1000");
			accbal=sc.nextDouble();sc.nextLine();
		}
		String password="";int n=0;
		while(n<4)
		{
			password=password+(int)(Math.random()*10);
			n++;
		}
		ArrayList<Customer> list=DataClass.customer;
		//String userid, String password, String name, long mobileno, String dOB, String emailid,long accno,double accbal
		list.add(new Customer(custid,password,acc_name,acc_mobile,acc_DOB,acc_email,accno,accbal));
		System.out.println("CustomerId     AccountNumber  AccounBalance    PinNumber      CustomerName   DateOfBirth  MobileNumber   emailid                       ");
		list.get(list.size()-1).printCustomer();
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
		System.out.println("Enter the customer id to remove");
		String custid=sc.nextLine();
		System.out.println("Enter the customer name");
		String acc_name=sc.nextLine();
		ArrayList<Customer> list=DataClass.customer;
		for(int i=0;i<list.size();i++)
		{
			Customer cs=(Customer)list.get(i);
			if(cs.getUserid().equals(custid))
			{
				if( cs.getName().equalsIgnoreCase(acc_name))
				{
					list.remove(i);System.out.println("Removed successfully...");check=false;break;
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
		System.out.println("Enter the customerid");
		String custid=sc.nextLine();
		System.out.println("Enter the Account holder name");
		String acc_name=sc.nextLine();
		ArrayList<Customer> list=DataClass.customer;
		for(int i=0;i<list.size();i++)
		{
			Customer cs=(Customer)list.get(i);
			if(cs.getUserid().equals(custid))
			{
				if( cs.getName().equalsIgnoreCase(acc_name))
				{
					System.out.println("Enter 1.name update 2.mobile number update 3.emailid update 4.pin update");
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
					case 4:System.out.println("Enter your new pin number");
							String newpin=sc.nextLine();
							list.get(i).setPassword(newpin);
							System.out.println("Updated successfully");check=false;break;
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
	
	public void searchusers()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("search customer details Press 1.By customerid 2.accountholder name 3.mobileno 4.emailid 5.Account number");
		int num=sc.nextInt();sc.nextLine();
		ArrayList<Customer> list=DataClass.customer;
		switch(num)
		{
		case 1:searchbycustid(list);break;
		case 2:searchbyname(list);break;
		case 3:searchbymobile(list);break;
		case 4:searchbyemailid(list);break;
		case 5:searchbyaccno(list);break;
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
		ArrayList<Employee> list=DataClass.employee;
		//while(list==null)
		{
			for(Object ob:list)
			{
				Employee cs=(Employee)ob;
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
							System.out.println("Your new password ="+password1);
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
	public void giveloan()
	{
		boolean check=true;
		long loanaccno=0;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the customer id");
		String custid=sc.nextLine();
		System.out.println("Enter the Account holder name");
		String acc_name=sc.nextLine();
		ArrayList<Customer> list=DataClass.customer;
		for(int i=0;i<list.size();i++)
		{
			Customer cs=(Customer)list.get(i);
			if(cs.getUserid().equals(custid))
			{
				if( cs.getName().equalsIgnoreCase(acc_name))
				{
					System.out.println("Enter your Loan amount");
					double amt=sc.nextDouble();sc.nextLine();
					System.out.println("Enter the no of year to pay the loan");
					double year=sc.nextDouble();sc.nextLine();
					Loan loantype=null;
					int num=0;
					while(loantype==null)
					{
						System.out.println("Enter 1.HomeLoan 2.CarLoan 3.BikeLoan 4.PersonalLoan");
						num=sc.nextInt();sc.nextLine();
						loantype=LoanFactory.selectLoan(num);
					}
					String loanid="";int loanno;
					
					double accbal=0;
					for(int j=1;j<cs.getAccount().size();j++)
					{
						accbal=accbal+cs.getAccount().get(j).getAccbal();
					}
					if(amt+accbal<=500000)
					{
						loanaccno=AccountDataClass.Loan_Acc_no++;
						switch(num)
						{
							case 1: loanno=AccountDataClass.houseloanno++;
									loanid="HL0"+loanno;break;
							case 2: loanno=AccountDataClass.carloanno++;
									loanid="CL0"+loanno;break;
							case 3: loanno=AccountDataClass.bikeloanno++;
									loanid="BL0"+loanno;break;
							case 4: loanno=AccountDataClass.personalloanno++;
									loanid="PL0"+loanno;break;
						}
						double interest=(double)Math.round(amt*year*loantype.getLoanRate())/100;
						double totalpay=amt+interest;
						double monthlyprinciple=(double)Math.round(amt*100/(12*year))/100;
						double monthlyinterest=(double)Math.round(interest*100/(12*year))/100;
						
						cs.getAccount().add(new LoanAccount(loantype, loanaccno, amt, loanid,monthlyinterest,monthlyprinciple, year, totalpay, new Date()));
						System.out.println("Loan is sanctioned successfully.....");
						int index=cs.getAccount().size()-1;
						System.out.println("LoanAccNo      AccountBal     LoanID     InterestPerMonth  PrinciplePerMonth  TotalAmountPerMonth  No_Of_Months  Totalamount   LoanDate");
						cs.getAccount().get(index).printLoanDetails();
					}
					else 
						System.out.println("Sorry max loan limit is Rs5,00,000 but you already take Rs"+accbal);
					check=false;break;
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
	
	public void viewTransactionDetails()
	{
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Account Number to view Transaction details...");
		long accno=sc.nextLong();sc.nextLine();
		System.out.println("Enter the customer name");
		String name=sc.nextLine();
		ArrayList<Customer> list=DataClass.customer;
		for(Object ob:list)
		{
			Customer cs=(Customer)ob;	
			for(int i=0;i<cs.getAccount().size();i++)
			{
				if(cs.getAccount().get(i).getAccno()==accno)
				{
					check=false;
					if(cs.getName().equalsIgnoreCase(name))
					{
						System.out.println("Transaction History of AccNo="+cs.getAccount().get(i).getAccno());
						System.out.println("S.no  Last_balance     Withdraw_Amount  Deposit_Amount   Current_Balance  transaction_date");
						cs.getAccount().get(i).printTrans_History();break;
					}
					else
					{
						System.out.println("Sorry account holder name is wrong...");break;
					}
				}
			}
			if(check==false)
				break;
		}
		if(check==true)
			System.out.println("Sorry this account number is wrong..");
	}
	public void viewLoanDetails()
	{
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the customer id ");
		String custid=sc.nextLine();
		System.out.println("Enter your customer name");
		String pass=sc.nextLine();
		ArrayList<Customer> list=DataClass.customer;
		//while(list==null)
			boolean isloan=false;
			for(Object ob:list)
			{
				Customer cs=(Customer)ob;
				if(cs.getUserid().equals(custid))
				{	
					check=false;
					if(cs.getName().equalsIgnoreCase(pass))
					{
						double sumofaccbal=0,monthlyinterest=0,monthlyprinciple=0,monthlytotalpay=0,total=0;
						if(cs.getAccount().size()>1)
							System.out.println("LoanAccNo      AccountBal     LoanID     InterestPerMonth  PrinciplePerMonth  TotalAmountPerMonth  No_Of_Months  Totalamount   LoanDate");
							//15,15,11,18,19,21,14,14
						for(int i=1;i<cs.getAccount().size();i++)
						{
							LoanAccount ac=(LoanAccount)cs.getAccount().get(i);
							ac.printLoanDetails();
							sumofaccbal+=ac.getAccbal();
							monthlyinterest+=ac.getMonthlyInterest();
							monthlyprinciple+=ac.getMonthlyprinciple();
							monthlytotalpay+=ac.getMonthlytotalpay();
							total+=ac.getTotalpay();
							isloan=true;
						}
						if(isloan==true)
						{
							System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
							System.out.print("Total          ");
							System.out.print(sumofaccbal);
							String str=""+sumofaccbal;
							for(int i=str.length();i<26;i++)
								System.out.print(" ");
							System.out.print(monthlyinterest);
							str=""+monthlyinterest;
							for(int i=str.length();i<18;i++)
								System.out.print(" ");
							System.out.print(monthlyprinciple);
							str=""+monthlyprinciple;
							for(int i=str.length();i<19;i++)
								System.out.print(" ");
							System.out.print(monthlytotalpay);
							str=""+monthlytotalpay;
							for(int i=str.length();i<35;i++)
								System.out.print(" ");
							System.out.print(total);
							str=""+total;
							for(int i=str.length();i<14;i++)
								System.out.print(" ");
							System.out.println();
						}
						/*else if(isloan==false)
							System.out.println("this customer has no loan");
					*/
					}
					else
					{
						System.out.println("Sorry customer name is incorrect...");
						break;
					}
				}					
			}
			if(check==true)
				System.out.println("Customer id is incorrect...");
			//list=(ArrayList)ois.readObject();
	}
	public void viewAllUsers()
	{
		ArrayList<Customer> list=DataClass.customer;	
		if(list.size()==0)
			System.out.println("Sorry you dont have any customers....");
		else
		{	
			System.out.println("CustomerId     AccountNumber  AccounBalance    PinNumber      CustomerName   DateOfBirth  MobileNumber   emailid                       ");
			for(Object ob:list)
			{
				Customer cs=(Customer)ob;
				cs.printCustomer();
				/*cs.getAccount().get(0).getAccbal();
				for(int i=1;i<cs.getAccount().size();i++)
				{
					cs.getAccount().get(i).printLoanDetails();
				}	
				*/				
			}
		}
		
	}
	public void deposit()
	{
		ArrayList<Customer> list=DataClass.customer;
		Scanner sc=new Scanner(System.in);
		//System.out.println("Which account you are going to deposit 1.SavingsAccount 2.LoanAccount");
		//int num=sc.nextInt();sc.nextLine();
		System.out.println("Enter the accountnumber to deposit");
		long accno=sc.nextLong();sc.nextLine();
		System.out.println("Enter the account holder name");
		String custname=sc.nextLine();
		boolean check=false,isloan=false;
		for(int i=0;i<list.size();i++)
		{
			Customer cs=list.get(i);
			for(int j=0;j<cs.getAccount().size();j++)
			{
				if(accno==cs.getAccount().get(j).getAccno())
				{
					check=true;
					if(cs.getName().equalsIgnoreCase(custname))
					{
						if(cs.getAccount().get(j) instanceof LoanAccount)
						{//18,19,20
							isloan=true;
							LoanAccount la=(LoanAccount)cs.getAccount().get(j);
							System.out.println("AccountNumber     MonthlyInterest    MonthlyPrinciple    TotalPay");
							System.out.print(la.getAccno());
							for(int k=String.valueOf(la.getAccno()).length();k<18;k++)
								System.out.print(" ");
							System.out.print(la.getMonthlyInterest());
							for(int k=String.valueOf(la.getMonthlyInterest()).length();k<19;k++)
								System.out.print(" ");
							System.out.print(la.getMonthlyprinciple());
							for(int k=String.valueOf(la.getMonthlyprinciple()).length();k<20;k++)
								System.out.print(" ");
							System.out.print(la.getMonthlyInterest()+la.getMonthlyprinciple());
							System.out.println();
							System.out.println("Press 1.To Pay From your SavingsAccount 2.DirectPayment Through Employee");
							int num=sc.nextInt();sc.nextLine();
							if(num==1)
							{
								if(cs.getAccount().get(0).getAccbal()>=((double)Math.ceil(la.getMonthlytotalpay())))
								{
									cs.getAccount().get(0).withdraw((double)Math.ceil(la.getMonthlytotalpay()));
									cs.getAccount().get(j).deposit((double)Math.ceil(la.getMonthlytotalpay()));
									
								}
								else
								{
									System.out.println("Sorry you don't have enough balance..");
								}
							}
							else if(num==2)
							{
								System.out.println("Account holder need to pay Rs"+Math.ceil(la.getMonthlyInterest()+la.getMonthlyprinciple()));
								System.out.println("If account holder pay the money please proceed ...");
								System.out.println("1.Proceed with payment 2.Cancel This Payment");
								int pay=sc.nextInt();sc.nextLine();
								if(pay==1)
								{
									cs.getAccount().get(j).deposit(Math.ceil(la.getMonthlyInterest()+la.getMonthlyprinciple()));
			
								}
								else
								{
									System.out.println("Please pay back the loan pay soon...");break;
								}
							}
							else
							{
								System.out.println("Invalid input...");break;
							}
							if(la.getAccbal()<1)
							{
								cs.getAccount().remove(j);
								System.out.println("This loan completed Successfully....");break;
							}
							else
							{
								System.out.println("Still your need to pay the loan for further "+la.getMonth()+" months");break;
								
							}
						}
						if(isloan==false)
						{
							System.out.println("Enter the amount to deposit");
							double amt=sc.nextDouble();sc.nextLine();
							cs.getAccount().get(0).deposit(amt);break;
						}
					}
					else
					{
						System.out.println("Sorry account number and account holder name mismatched");
						break;
					}
				}
			}
		}
		if(check==false)
			System.out.println("Sorry this account number is not available...");	
	}
	public void withdraw()
	{
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the account number");
		long accno=sc.nextLong();sc.nextLine();
		System.out.println("Enter the account holder name");
		String name=sc.nextLine();
		
		ArrayList<Customer> list=DataClass.customer;
		for(int i=0;i<list.size();i++)
		{
			Object ob=list.get(i);
			Customer cs=(Customer)ob;
			if(cs.getAccount().get(0).getAccno()==accno)
			{	check=false;	
				if(name.equalsIgnoreCase(cs.getName()))
				{
					System.out.println("Enter your withdraw amount");
					double amt=Math.abs(sc.nextDouble());
					sc.nextLine();
					Account curr=cs.getAccount().get(0);
					if(amt<=curr.getAccbal())
					{
						curr.withdraw(amt);
						check=false;
						break;
					}
					else
					{
						System.out.println("Sorry your account balance is less than your withdrawal amount..");check=false;break;
					}
					
				}
				else
				{
					System.out.println("Sorry name and accno mismatched...");check=false;break;
				}
				
			}
		}
		if(check==true)
			System.out.println("Sorry this account number is wrong..");
	}
	public void printLoanRate()
	{
		System.out.println("LoanType      LoanRatePerYear in (%)");
		System.out.println("------------------------------------");
		System.out.println("HomeLoan      "+new HomeLoan().getLoanRate());
		System.out.println("CarLoan       "+new CarLoan().getLoanRate());
		System.out.println("BikeLoan      "+new BikeLoan().getLoanRate());
		System.out.println("PersonalLoan  "+new PersonalLoan().getLoanRate());
	}
	public void searchbyname(ArrayList list)
	{
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the account holder name");
		String name=sc.nextLine();
		for(int i=0;i<list.size();i++)
		{
			Customer cs=(Customer)list.get(i);
			if(cs.getName().equalsIgnoreCase(name))
			{		
				System.out.println("CustomerId     AccountNumber  AccounBalance    PinNumber      CustomerName   DateOfBirth  MobileNumber   emailid                       ");
				cs.printCustomer();
				if(cs.getAccount().size()>1)
					System.out.println("LoanAccNo      AccountBal     LoanID     InterestPerMonth  PrinciplePerMonth  TotalAmountPerMonth  No_Of_Months  Totalamount   LoanDate");
				for(int j=1;j<cs.getAccount().size();j++)
				{
					cs.getAccount().get(j).printLoanDetails();
				}
				//System.out.println(cs.getAccount().toString());
				check=false;break;
			}
		}
		if(check==true)
			System.out.println("this details is not available");
	}
	public void searchbycustid(ArrayList list)
	{
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the customerid");
		String custid=sc.nextLine();
		for(int i=0;i<list.size();i++)
		{
			Customer cs=(Customer)list.get(i);
			if(cs.getUserid().equals(custid))
			{
				System.out.println("CustomerId     AccountNumber  AccounBalance    PinNumber      CustomerName   DateOfBirth  MobileNumber   emailid                       ");
				cs.printCustomer();
				if(cs.getAccount().size()>1)
					System.out.println("LoanAccNo      AccountBal     LoanID     InterestPerMonth  PrinciplePerMonth  TotalAmountPerMonth  No_Of_Months  Totalamount   LoanDate");
				for(int j=1;j<cs.getAccount().size();j++)
				{
					cs.getAccount().get(j).printLoanDetails();
				}
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
		System.out.println("Enter the account holder mobile numer");
		long acc_mobile=sc.nextLong();sc.nextLine();
		for(int i=0;i<list.size();i++)
		{
			Customer cs=(Customer)list.get(i);
			if(cs.getMobileno()==acc_mobile)
			{
				System.out.println("CustomerId     AccountNumber  AccounBalance    PinNumber      CustomerName   DateOfBirth  MobileNumber   emailid                       ");
				cs.printCustomer();
				if(cs.getAccount().size()>1)
					System.out.println("LoanAccNo      AccountBal     LoanID     InterestPerMonth  PrinciplePerMonth  TotalAmountPerMonth  No_Of_Months  Totalamount   LoanDate");
				for(int j=1;j<cs.getAccount().size();j++)
				{
					cs.getAccount().get(j).printLoanDetails();
				}
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
		System.out.println("Enter the account holder mailid");
		String acc_email=sc.nextLine();
		for(int i=0;i<list.size();i++)
		{
			Customer cs=(Customer)list.get(i);
			if(cs.getEmailid().equalsIgnoreCase(acc_email))
			{
				System.out.println("CustomerId     AccountNumber  AccounBalance    PinNumber      CustomerName   DateOfBirth  MobileNumber   emailid                       ");
				cs.printCustomer();
				if(cs.getAccount().size()>1)
					System.out.println("LoanAccNo      AccountBal     LoanID     InterestPerMonth  PrinciplePerMonth  TotalAmountPerMonth  No_Of_Months  Totalamount   LoanDate");
				for(int j=1;j<cs.getAccount().size();j++)
				{
					cs.getAccount().get(j).printLoanDetails();
				}				check=false;break;
			}
		}
		if(check==true)
			System.out.println("this details is not available");
	}
	
	public void searchbyaccno(ArrayList list)
	{
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the account number");
		long accno=sc.nextLong();sc.nextLine();
		for(int i=0;i<list.size();i++)
		{
			Customer cs=(Customer)list.get(i);
			for(Account ac:cs.getAccount())
			
			{
				if(ac.getAccno()==accno)
				{
					System.out.println("CustomerId     AccountNumber  AccounBalance    PinNumber      CustomerName   DateOfBirth  MobileNumber   emailid                       ");
					cs.printCustomer();
					if(cs.getAccount().size()>1)
						System.out.println("LoanAccNo      AccountBal     LoanID     InterestPerMonth  PrinciplePerMonth  TotalAmountPerMonth  No_Of_Months  Totalamount   LoanDate");
					for(int j=1;j<cs.getAccount().size();j++)
					{
						cs.getAccount().get(j).printLoanDetails();
					}				check=false;break;
				}
			}
		}
		if(check==true)
			System.out.println("this details is not available");
	}
	
}
