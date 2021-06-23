package com.TMB.Customer;

import java.util.Scanner;
import com.TMB.Account.Account;
import com.TMB.Account.LoanAccount;
import com.TMB.Account.LoanAccountDB;
import com.TMB.Account.SavingsAccount;
import com.TMB.Account.SavingsAccountDB;
import com.TMB.Account.TransactionDB;

import java.util.ArrayList;
public class Customer implements CustomerInterface{
	private String userid;
	transient private String password;
	private String name;
	private long mobileno;
	private String  DOB;
	private String emailid;
	public Customer(String userid, String password, String name, long mobileno, String dOB, String emailid) 
	{
		super();
		this.userid = userid;
		this.password = password;
		this.name = name;
		this.mobileno = mobileno;
		this.DOB = dOB;
		this.emailid = emailid;
	}
	public Customer() {
		super();
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public void printCustomer()
	{
		Account sav=SavingsAccountDB.selectAccount(this.getUserid());
		//15,15,15,13,15
		//System.out.println("CustomerId     AccountNumber  AccounBalance    PinNumber      CustomerName   DateOfBirth  MobileNumber   emailid                       ");
		System.out.print(userid);
		for(int i=userid.length();i<15;i++)
			System.out.print(" ");
		System.out.print(sav.getAccno());
		String str=String.valueOf(sav.getAccno());
		for(int i=str.length();i<15;i++)
			System.out.print(" ");
		double bal=sav.getAccbal();
		System.out.print(bal);
		str=String.valueOf(bal);
		for(int i=str.length();i<17;i++)
			System.out.print(" ");
		System.out.print(password);
		for(int i=password.length();i<15;i++)
			System.out.print(" ");
		System.out.print(name);
		for(int i=name.length();i<15;i++)
			System.out.print(" ");
		System.out.print(DOB);
		for(int i=DOB.length();i<13;i++)
			System.out.print(" ");
		System.out.print(mobileno);
		str=String.valueOf(mobileno);
		for(int i=str.length();i<15;i++)
			System.out.print(" ");
		System.out.print(emailid);
		for(int i=emailid.length();i<30;i++)
			System.out.print(" ");
		System.out.println();
	}
	
	public CustomerInterface login()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your customer id");
		String custid=sc.nextLine();
		System.out.println("Enter your Current pin number");
		String pass=sc.nextLine();
		Customer cs=CustomerDB.selectCustomer(custid);
		if(cs!=null)
		{	
			
			if(cs.getPassword().equals(pass))
			{
				System.out.println("Successfully Logged in...");	
				return cs;
			}
			else
			{
				System.out.println("Sorry your pin number is incorrect...");
				return null;
			}
		}
		else
			System.out.println("Sorry this account number is wrong..");
		return null;
	}
	public void withdraw()
	{
		Scanner sc=new Scanner(System.in);
		String custid=this.getUserid();
		System.out.println("Enter your pin number");
		String pass=sc.nextLine();
		Customer cust=CustomerDB.selectCustomer(custid);
		if(cust !=null)
		{		
			if(pass.equals(cust.getPassword()))
			{
				System.out.println("Enter your withdraw amount");
				double amt=Math.abs(sc.nextDouble());
				sc.nextLine();
				Account curr=SavingsAccountDB.selectAccount(custid);
				if(amt<=curr.getAccbal())
				{
					curr.withdraw(amt);
					SavingsAccountDB.updateAccount((SavingsAccount)curr);
				}
				else
				{
					System.out.println("Sorry Insufficient balance .Your current balance is Rs."+curr.getAccbal());
				}
				
			}
			else
			{
				System.out.println("Sorry your pin number is incorrect...");
			}
			
		}
		
	}
	
	public void balanceEnquiry()
	{
		Scanner sc=new Scanner(System.in);
		String custid=this.userid;
		System.out.println("Enter your pin no");
		String pass=sc.nextLine();
		Customer cs=CustomerDB.selectCustomer(custid);
		if(cs!=null)
		{	
			if(pass.equals(cs.getPassword()))
			{
				System.out.println("Which account balance you want 1.SavingsAccount 2.LoanAccount");
				int choice=sc.nextInt();sc.nextLine();
				if(choice==1)
				{
					Account savacc=SavingsAccountDB.selectAccount(custid);
					System.out.println("Your Account balance is Rs"+savacc.getAccbal());
				}
				else if(choice==2)
				{
					ArrayList<Account> loan=LoanAccountDB.selectAccount(custid);
					if(loan.size()>0)
					{	
						System.out.println("LoanAccNo      AccountBal     LoanID     InterestPerMonth  PrinciplePerMonth  TotalAmountPerMonth  No_Of_Months  Totalamount   LoanDate");
						for(int i=0;i<loan.size();i++)
						{
							loan.get(i).printLoanDetails();
						}
					}
					else 
						System.out.println("You dont have any loan....");
					
				}
				
				else
					System.out.println("Invalid input.....");
			}
			else
				System.out.println("Sorry your pin number is incorrect...");
		}	
		else
			System.out.println("Sorry this account number is wrong..");
		
	}
	public void transferAmt()
	{
		Scanner sc=new Scanner(System.in);
		String user_id=this.getUserid();
		System.out.println("Enter your pin no");
		String pass=sc.nextLine();
		Customer cs=CustomerDB.selectCustomer(user_id);
		if(cs!=null)
		{	
			if(pass.equals(cs.getPassword()))
			{
				Account savacc=SavingsAccountDB.selectAccount(user_id);
				System.out.println("Enter the receiver Account number");
				long receiveracc=sc.nextLong();sc.nextLine();
				System.out.println("Re-Enter the receiver Account number");
				long receiveracc1=sc.nextLong();sc.nextLine();
				if(savacc.getAccno()==receiveracc)
				{
					System.out.println("Sorry you are trying to transfer amount to your own account...please check the receiver account number");
					
				}
				if(receiveracc==receiveracc1)
				{
					Account rec=SavingsAccountDB.selectAccount(receiveracc1);
					if(rec!=null)
					{
						System.out.println("Enter the amount you want to transfer");
						double amount=sc.nextDouble();sc.nextLine();
						if(amount<=savacc.getAccbal())
						{
							savacc.withdraw(amount);
							rec.deposit(amount);
							SavingsAccountDB.updateAccount((SavingsAccount)savacc);
							SavingsAccountDB.updateAccount((SavingsAccount)rec);
							System.out.println("Amount Transfer successfully...");
						}
						else
						{
							System.out.println("Sorry Insufficient Balance...");
						}
					}
					else
					{
						System.out.println("Sorry...receiver account number is invalid...");
					}
					
				}
				else
				{
					System.out.println("Sorry...Two account numbers are mismatched...");
				}	
			}
			else
			{
				System.out.println("Sorry your pin number is incorrect...");
				
			}
		}
		else
			System.out.println("Sorry this account number is wrong..");
	}
	public void changePassword()
	{
		Scanner sc=new Scanner(System.in);
		String custid=this.getUserid();
		System.out.println("Enter your Current pin number");
		String pass=sc.nextLine();
		Customer cs=CustomerDB.selectCustomer(custid);
		if(cs!=null)
		{	
			if(cs.getPassword().equals(pass))
			{
				System.out.println("Enter your new pin");
				String pin1=sc.nextLine();
				System.out.println("Re-Enter your password");
				String pin2=sc.nextLine();
				if(pin1.equals(pin2))
				{
					cs.setPassword(pin2);
					CustomerDB.updateCustomer(cs);
					System.out.println("Pin is successfully changed...");
				}
				else
				{
					System.out.println("OOPS...Both pins are not same...");
				}
			}
			else
			{
				System.out.println("Sorry your pin number is incorrect...");
			}
		}
		else
			System.out.println("Sorry this account number is wrong..");
	}
	public void printLoanDetails()
	{
		Scanner sc=new Scanner(System.in);
		String custid=this.getUserid();
		System.out.println("Enter your Current pin number");
		String pass=sc.nextLine();
		Customer cs=CustomerDB.selectCustomer(custid);
		if(cs!=null)
		{	
			if(cs.getPassword().equals(pass))
			{
				ArrayList<Account> acc=LoanAccountDB.selectAccount(custid);
				double sumofaccbal=0,monthlyinterest=0,monthlyprinciple=0,monthlytotalpay=0,total=0;
				if(acc.size()>0)
				{
					System.out.println("LoanAccNo      AccountBal     LoanID     InterestPerMonth  PrinciplePerMonth  TotalAmountPerMonth  No_Of_Months  Totalamount   LoanDate");
					//15,15,11,18,19,21,14,14
					for(int i=0;i<acc.size();i++)
					{
						LoanAccount ac=(LoanAccount)acc.get(i);
						ac.printLoanDetails();
						sumofaccbal+=ac.getAccbal();
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
					System.out.println("this customer has no loan");
			}
			else
			{
				System.out.println("Sorry your pin number is incorrect...");
				
			}
		}
		else
			System.out.println("Sorry this account number is wrong..");
	}
	public void viewTransactionDetails()
	{
		Scanner sc=new Scanner(System.in);
		String custid=this.getUserid();
		System.out.println("Enter your Current pin number");
		String pass=sc.nextLine();
		Customer cs=CustomerDB.selectCustomer(custid);
		if(cs!=null)
		{	
			if(cs.getPassword().equals(pass))
			{
				System.out.println("Press 1.SavingsAccount Transaction 2.Loan Transaction 3.Both TransactionHistory");
				int num=sc.nextInt();sc.nextLine();
				if(num>=1&&num<=3)
				{
					
					if(num==1||num==3)
					{
						Account savacc=SavingsAccountDB.selectAccount(custid);
						System.out.println("Transaction History of AccNo="+savacc.getAccno());
						System.out.println("Last_balance     Withdraw_Amount  Deposit_Amount   Current_Balance  transaction_date");
						TransactionDB.printTransactionDetails(savacc.getAccno());
					}
					if(num==2||num==3)
					{
						ArrayList<Account> list=LoanAccountDB.selectAccount(custid);
						for(int i=0;i<list.size();i++)
						{
							System.out.println("Transaction History of AccNo="+list.get(i).getAccno());
							System.out.println("Last_balance     Withdraw_Amount  Deposit_Amount   Current_Balance  transaction_date");
							TransactionDB.printTransactionDetails(list.get(i).getAccno());
						}
					}
				}
				else
					System.out.println("invalid input...");
			}
			else
			{
				System.out.println("Sorry your pin number is incorrect...");
			}
		}
		else
			System.out.println("Sorry this account number is invalid..");
	}
	
	public void deposit()
	{
		Scanner sc=new Scanner(System.in);
		String custid=this.getUserid();
		//System.out.println("Enter your pin number");
	//	String pinno=sc.nextLine();
		Customer cs=CustomerDB.selectCustomer(custid);
		if(cs!=null)
		{	
			//if(cs.getPassword().equals(pinno))
			{
				ArrayList<Account> loanacc=LoanAccountDB.selectAccount(custid);
				if(loanacc.size()>0)
				{
					System.out.println("The following is your Loan details...");
					for(int j=0;j<loanacc.size();j++)
					{
						
						this.printLoanDetails();
					}
						
						ArrayList<Account> acc=LoanAccountDB.selectAccount(custid);
						if(acc!=null)
						{
							System.out.println("Please Enter the number for the account which you want to deposit");
							for(int i=0;i<acc.size();i++)
							{
								System.out.println(i+"="+acc.get(i).getAccno());
							}
							int accno=sc.nextInt();sc.nextLine();
							if(accno>=0 &&accno<acc.size())
							{
								//18,19,20
								LoanAccount la=(LoanAccount)acc.get(accno);
								System.out.println("Your loan monthly pay slip for this account...");
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
								System.out.print((double)Math.round(la.getMonthlyInterest()+la.getMonthlyprinciple()*100)/100);
								System.out.println();
								System.out.println("Press 1.To Proceed with the payment 2.To quit deposit");
								int choice=sc.nextInt();sc.nextLine();
								switch(choice)
								{
								case 1:Account sav=SavingsAccountDB.selectAccount(custid);
										if(sav.getAccbal()>=Math.ceil(la.getMonthlytotalpay()))
										{
											sav.withdraw(Math.ceil(la.getMonthlyInterest()+la.getMonthlyprinciple()));
											la.deposit(Math.ceil(la.getMonthlyInterest()+la.getMonthlyprinciple()));
											SavingsAccountDB.updateAccount((SavingsAccount)sav);
											LoanAccountDB.updateAccount(la);
										}
										else
										{
											System.out.println("Sorry you don't have enough balance..");
										}break;
								case 2:System.out.println("Please pay the loan soon..");break;
								default:System.out.println("Invalid input...");
								}
								if(la.getAccbal()<1)
								{
									LoanAccountDB.deleteAccount(la.getAccno());
								}
								else
								{
									System.out.println("Still your need to pay the loan for further "+la.getMonth()+" months");
									
								}
							}
							else
								System.out.println("Sorry invalid input...");
						}
					}
					else
						System.out.println("Sorry you dont have any loan account");
				}
				//else
					//System.out.println("Sorry wrong pin no..");
			}	
	}
}
