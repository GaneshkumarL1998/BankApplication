package com.TMB.Employee;


import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


import com.TMB.Account.Account;
import com.TMB.Account.BikeLoan;
import com.TMB.Account.CarLoan;
import com.TMB.Account.HomeLoan;
import com.TMB.Account.Loan;
import com.TMB.Account.LoanAccount;
import com.TMB.Account.LoanAccountDB;
import com.TMB.Account.LoanFactory;
import com.TMB.Account.PersonalLoan;
import com.TMB.Account.SavingsAccount;
import com.TMB.Account.SavingsAccountDB;
import com.TMB.Account.TransactionDB;
import com.TMB.Customer.Customer;
import com.TMB.Customer.CustomerDB;
import com.TMB.datafiles.AccountDataClass;



public class Employee implements EmployeeInterface{
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
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your user id");
		String e_userid=sc.nextLine();
		System.out.println("Enter your Current Password");
		String e_password=sc.nextLine();
		Employee ad=EmployeeDB.selectEmployee(e_userid);
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
		long accno=AccountDataClass.Savings_Acc_no++;
		String custid=""+AccountDataClass.customerid;
		AccountDataClass.customerid++;
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
		//String userid, String password, String name, long mobileno, String dOB, String emailid,ArrayList<Long>acc		
		Customer cust=new Customer(custid,password,acc_name,acc_mobile,acc_DOB,acc_email);
		
		if(CustomerDB.insertCustomer(cust)==true)
		{
			SavingsAccountDB.insertAccount(new SavingsAccount(accno,accbal,custid));
			System.out.println("CustomerId     AccountNumber  AccounBalance    PinNumber      CustomerName   DateOfBirth  MobileNumber   emailid                       ");
			cust.printCustomer();
		}
		else
		{
			System.out.println("Sorry data is not inserted ...");
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
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the customer id to remove");
		String custid=sc.nextLine();
		System.out.println("Enter the customer name");
		String acc_name=sc.nextLine();
		Customer cs=CustomerDB.selectCustomer(custid);		
		if(cs!=null)
		{
			if(cs.getName().equalsIgnoreCase(acc_name))
			{
				ArrayList<Account> ac=LoanAccountDB.selectAccount(custid);
				if(ac.size()<=0)
				{
					CustomerDB.removeCustomer(custid);
					SavingsAccountDB.deleteAccount(SavingsAccountDB.selectAccount(custid).getAccno());
				}
				else
				{
					System.out.println("Sorry you still have loan..please pay it and then close the account");
				}
			}
			else
			{
				System.out.println("account holder name is wrong");
			}
		}
		else
			System.out.println("this details is not available");
	}
	public void updateuser()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the customerid");
		String custid=sc.nextLine();
		System.out.println("Enter the Account holder name");
		String acc_name=sc.nextLine();
		Customer cs=CustomerDB.selectCustomer(custid);
		if(cs!=null)
		{
			if( cs.getName().equalsIgnoreCase(acc_name))
			{
				System.out.println("Enter 1.name update 2.mobile number update 3.emailid update 4.pin update");
				int choice=sc.nextInt();sc.nextLine();
				switch(choice)
				{
				case 1:System.out.println("Enter your new update in name");
						String newname=sc.nextLine();
						cs.setName(newname);
						CustomerDB.updateCustomer(cs);
						System.out.println("Name Updated successfully");break;
				case 2:long acc_mobile;
						while(true)
						{System.out.println("Enter the new Mobile number");
							acc_mobile=sc.nextLong();
							sc.nextLine();
							if(String.valueOf(acc_mobile).length()==10)
								break;
							else
								System.out.println("Sorry mobile number must be in 10 digits.Re-enter mobileno");
						}
						cs.setMobileno(acc_mobile);
						CustomerDB.updateCustomer(cs);
						System.out.println("mobile no Updated successfully");break;
				case 3:String acc_email="";
						while(acc_email.contains("@")!=true)
						{
							System.out.println("Enter the employee email id");
							acc_email=sc.nextLine();
							if(acc_email.contains("@")==false)
								System.out.println("mail id must contails @ operator");
						}
						cs.setEmailid(acc_email);CustomerDB.updateCustomer(cs);
						System.out.println("Emailid Updated successfully");break;
				case 4:System.out.println("Enter your new pin number");
						String newpin=sc.nextLine();
						cs.setPassword(newpin);CustomerDB.updateCustomer(cs);
						System.out.println("Pin Number Updated successfully");break;
				default :System.out.println("Invalid input");
				}	
			}
			else
			{
				System.out.println("account holder name is wrong");
			}
		}
		else
			System.out.println("this details is not available");
	}
	
	public void searchusers()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("search customer details Press 1.By customerid 2.accountholder name 3.mobileno 4.emailid");
		int num=sc.nextInt();sc.nextLine();
		switch(num)
		{
		case 1:System.out.println("Enter customerid of accountholer");String custid=sc.nextLine();
				System.out.println("CustomerId     AccountNumber  AccounBalance    PinNumber      CustomerName   DateOfBirth  MobileNumber   emailid                       ");
				CustomerDB.searchCustomer("custid",custid);break;
		case 2:System.out.println("Enter Name of accountholer");String name=sc.nextLine();
				System.out.println("CustomerId     AccountNumber  AccounBalance    PinNumber      CustomerName   DateOfBirth  MobileNumber   emailid                       ");
				CustomerDB.searchCustomer("c_name",name);break;
		case 3:System.out.println("Enter mobile no of accountholer");long mobileno=sc.nextLong();sc.nextLine();
				System.out.println("CustomerId     AccountNumber  AccounBalance    PinNumber      CustomerName   DateOfBirth  MobileNumber   emailid                       ");
				CustomerDB.searchCustomer("phoneno",mobileno);break;
		case 4:System.out.println("Enter emailid of accountholer");String emailid=sc.nextLine();
				System.out.println("CustomerId     AccountNumber  AccounBalance    PinNumber      CustomerName   DateOfBirth  MobileNumber   emailid                       ");
				CustomerDB.searchCustomer("emailid",emailid);break;
		default:System.out.println("Invalid input");
		}
	}
	public void changePassword()
	{
		Scanner sc=new Scanner(System.in);
		String e_userid=this.getUserid();
		System.out.println("Enter your Current Password");
		String e_password=sc.nextLine();
		Employee cs=EmployeeDB.selectEmployee(e_userid);		
		if(cs!=null)
		{	
			if(e_password.equals(cs.getPassword()))
			{
				System.out.println("Enter your new password");
				String password1=sc.nextLine();
				System.out.println("Re-enter your password");
				String password2=sc.nextLine();
				if(password1.equals(password2))
				{
					cs.setPassword(password1);
					EmployeeDB.updateEmployee(cs);
				}
				else
					System.out.println("Sorry your new passwords are not matched...both are different");
			}
			else
			{
				System.out.println("Sorry your password is incorrect...");
			}
		}
		else
			System.out.println("Sorry this employee id is invalid..");
	}
	public void giveloan()
	{
		long loanaccno=0;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the customer id");
		String custid=sc.nextLine();
		System.out.println("Enter the Account holder name");
		String acc_name=sc.nextLine();
		Customer cs=CustomerDB.selectCustomer(custid);
		if(cs!=null)
		{
			if( cs.getName().equalsIgnoreCase(acc_name))
			{
				System.out.println("Enter your Loan amount");
				double amt=sc.nextDouble();sc.nextLine();
				double accbal=0;
				ArrayList<Account> ac=LoanAccountDB.selectAccount(custid);
				for(int j=0;j<ac.size();j++)
				{
					accbal=accbal+ac.get(j).getAccbal();
				}
				if(amt+accbal<=500000)
				{
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
					double monthlytotalpay=monthlyinterest+monthlyprinciple;
					LoanAccount la=new LoanAccount(custid,loanaccno, amt, loanid,monthlyinterest,monthlyprinciple,monthlytotalpay, year*12, totalpay, new Date());
					LoanAccountDB.insertAccount(la);				
					System.out.println("LoanAccNo      AccountBal     LoanID     InterestPerMonth  PrinciplePerMonth  TotalAmountPerMonth  No_Of_Months  Totalamount   LoanDate");
					la.printLoanDetails();
				}
				else 
					System.out.println("Sorry max loan limit is Rs5,00,000 but you already take Rs"+accbal);
				
			}
			else
			{
				System.out.println("account holder name is wrong");
			}
		}
		else
			System.out.println("this details is not available");
	}
	
	public void viewTransactionDetails()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Account Number to view Transaction details...");
		long accno=sc.nextLong();sc.nextLine();
		System.out.println("Enter the customer name");
		String name=sc.nextLine();
		Account acc=LoanAccountDB.selectAccount(accno);
		if(acc==null)
		{
			acc=SavingsAccountDB.selectAccount(accno);	
		}
		if(acc!=null)
		{
			String cname=CustomerDB.selectCustomer(acc.getCustid()).getName();
			if(cname.equalsIgnoreCase(name))
			{
				System.out.println("Transaction History of AccNo="+acc.getAccno());
				System.out.println("Last_balance     Withdraw_Amount  Deposit_Amount   Current_Balance  transaction_date");
				TransactionDB.printTransactionDetails(acc.getAccno());
			}
			else
			{
				System.out.println("Sorry account holder name is wrong...");
			}
		}
		else
			System.out.println("Sorry this account number is wrong..");
	}
	public void viewLoanDetails()
	{
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the customer id ");
		String custid=sc.nextLine();
		System.out.println("Enter your customer name");
		String pass=sc.nextLine();
		Customer cs=CustomerDB.selectCustomer(custid);
		if(cs!=null)
		{	
			if(cs.getName().equalsIgnoreCase(pass))
			{
				ArrayList<Account> loan=LoanAccountDB.selectAccount(custid);
				double sumofaccbal=0,monthlyinterest=0,monthlyprinciple=0,monthlytotalpay=0,total=0;
				if(loan.size()>0)
				{	
					System.out.println("LoanAccNo      AccountBal     LoanID     InterestPerMonth  PrinciplePerMonth  TotalAmountPerMonth  No_Of_Months  Totalamount   LoanDate");
					//15,15,11,18,19,21,14,14
					for(int i=0;i<loan.size();i++)
					{
						LoanAccount ac=(LoanAccount)loan.get(i);
						ac.printLoanDetails();
						sumofaccbal+=ac.getAccbal();
						sumofaccbal=(double)Math.round(sumofaccbal*100)/100;
						monthlyinterest+=ac.getMonthlyInterest();
						monthlyprinciple+=ac.getMonthlyprinciple();
						monthlytotalpay+=ac.getMonthlytotalpay();
						total+=ac.getTotalpay();
						
					}
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
				else
					System.out.println("Sorry this cutomer has no loan..");
			}
			else
			{
				System.out.println("Sorry customer name is incorrect...");
			}
		}					
		else
			System.out.println("Customer id is invalid...");	
	}
	public void viewAllUsers()
	{
		CustomerDB.selectAllCustomer();		
	}
	public void deposit()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the accountnumber to deposit");
		long accno=sc.nextLong();sc.nextLine();
		System.out.println("Enter the account holder name");
		String custname=sc.nextLine();
		Account acc=SavingsAccountDB.selectAccount(accno);
		if(acc==null)
			acc=LoanAccountDB.selectAccount(accno);
		if(acc!=null)
		{	String cname=CustomerDB.selectCustomer(acc.getCustid()).getName();
			if(cname.equalsIgnoreCase(custname))
			{
				if(acc instanceof LoanAccount)
				{//18,19,20
					
					LoanAccount la=(LoanAccount)acc;
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
					System.out.print(la.getMonthlytotalpay());
					System.out.println();
					System.out.println("Press 1.To Pay From your SavingsAccount 2.DirectPayment Through Employee");
					int num=sc.nextInt();sc.nextLine();
					if(num==1)
					{
						SavingsAccount savacc=(SavingsAccount)SavingsAccountDB.selectAccount(la.getCustid());
						if(savacc.getAccbal()>=((double)Math.ceil(la.getMonthlytotalpay())))
						{
							savacc.withdraw((double)Math.ceil(la.getMonthlytotalpay()));
							la.deposit((double)Math.ceil(la.getMonthlytotalpay()));
							SavingsAccountDB.updateAccount(savacc);
							LoanAccountDB.updateAccount(la);
							
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
							la.deposit(Math.ceil(la.getMonthlyInterest()+la.getMonthlyprinciple()));
							LoanAccountDB.updateAccount(la);
						}
						else
						{
							System.out.println("Please pay back the loan pay soon...");
						}
					}
					else
					{
						System.out.println("Invalid input...");
					}
					if(la.getAccbal()<1)
					{
						LoanAccountDB.deleteAccount(accno);
						System.out.println("This loan completed Successfully....");
					}
					else
					{
						System.out.println("Still your need to pay the loan for further "+la.getMonth()+" months");
						
					}
				}
				else
				{
					System.out.println("Enter the amount to deposit");
					double amt=sc.nextDouble();sc.nextLine();
					acc.deposit(amt);
					SavingsAccountDB.updateAccount((SavingsAccount)acc);
				}
			}
			else
			{
				System.out.println("Sorry account number and account holder name mismatched");
				
			}
	}
	else
		System.out.println("Sorry this account number is invalid...");	
	}
	public void withdraw()
	{
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the account number");
		long accno=sc.nextLong();sc.nextLine();
		System.out.println("Enter the account holder name");
		String name=sc.nextLine();
		Account acc=SavingsAccountDB.selectAccount(accno);
		if(acc!=null)
		{	
			String cname=CustomerDB.selectCustomer(acc.getCustid()).getName();
			if(name.equalsIgnoreCase(cname))
			{
				System.out.println("Enter your withdraw amount");
				double amt=Math.abs(sc.nextDouble());
				sc.nextLine();
				if(amt<=acc.getAccbal())
				{
					acc.withdraw(amt);
					SavingsAccountDB.updateAccount((SavingsAccount)acc);
				}
				else
				{
					System.out.println("Sorry your account balance is less than your withdrawal amount..");
				}
				
			}
			else
			{
				System.out.println("Sorry name and accno mismatched...");
			}
			
		}
		else
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
	
}
