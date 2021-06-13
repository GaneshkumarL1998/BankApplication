package com.TMB.Customer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.TMB.Account.Account;
import com.TMB.Account.LoanAccount;
import com.TMB.Account.SavingsAccount;
import com.TMB.datafiles.DataClass;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
public class Customer implements CustomerInterface, Serializable{
	private static final long serialVersionUID=1L;
	//static DateFormat dateformat=new SimpleDateFormat("dd-MM-yyyy");
	private String userid;
	transient private String password;
	private String name;
	private long mobileno;
	private String  DOB;
	private String emailid;
	ArrayList <Account>account= new ArrayList<Account>();
	
	public Customer(String userid, String password, String name, long mobileno, String dOB, String emailid,long accno,double accbal) 
	{
		super();
		this.userid = userid;
		this.password = password;
		this.name = name;
		this.mobileno = mobileno;
		DOB = dOB;
		this.emailid = emailid;
		this.account.add(new SavingsAccount(accno,accbal));
	}
	public Customer() {
		super();
	}
	private void writeObject(ObjectOutputStream oos)throws Exception
	{
		oos.defaultWriteObject();
		String epassword=3257+password;
		oos.writeObject(epassword);
	}
	private void readObject(ObjectInputStream ois)throws Exception
	{
		ois.defaultReadObject();
		password=((String)ois.readObject()).substring(4);
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
	public ArrayList<Account> getAccount() {
		return account;
	}
	public void setAccount(ArrayList<Account> account) {
		this.account = account;
	}
	
	public void printCustomer()
	{
		//15,15,15,13,15
		//System.out.println("CustomerId     AccountNumber  AccounBalance    PinNumber      CustomerName   DateOfBirth  MobileNumber   emailid                       ");
		System.out.print(userid);
		for(int i=userid.length();i<15;i++)
			System.out.print(" ");
		System.out.print(this.getAccount().get(0).getAccno());
		String str=String.valueOf(this.getAccount().get(0).getAccno());
		for(int i=str.length();i<15;i++)
			System.out.print(" ");
		System.out.print(this.getAccount().get(0).getAccbal());
		str=String.valueOf(this.getAccount().get(0).getAccbal());
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
		boolean check=true;
		ArrayList<Customer> list=DataClass.customer;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your customer id");
		String custid=sc.nextLine();
		System.out.println("Enter your Current pin number");
		String pass=sc.nextLine();
		Customer ad=null;
		//while(list==null)
		{
			for(Object ob:list)
			{
				
				Customer cs=(Customer)ob;
				if(cs.getUserid().equals(custid))
				{	
					check=false;
					if(cs.getPassword().equals(pass))
					{
						System.out.println("Successfully Logged in...");
						ad=cs;
						break;	
					}
					else
					{
						System.out.println("Sorry your pin number is incorrect...");
						break;
					}
				}
					
			}
			//list=(ArrayList)ois.readObject();
		}
		if(check==true)
			System.out.println("Sorry this account number is wrong..");
		return ad;
	}
	public void withdraw()
	{
		Scanner sc=new Scanner(System.in);
		String custid=this.getUserid();
		System.out.println("Enter your pin number");
		String pass=sc.nextLine();
		
		ArrayList<Customer> list=DataClass.customer;
		for(int i=0;i<list.size();i++)
		{
			Object ob=list.get(i);
			Customer cs=(Customer)ob;
			if(custid.equals(cs.getUserid()))
			{		
				if(pass.equals(cs.getPassword()))
				{
					System.out.println("Enter your withdraw amount");
					double amt=Math.abs(sc.nextDouble());
					sc.nextLine();
					Account curr=cs.getAccount().get(0);
					if(amt<=curr.getAccbal())
					{
						curr.withdraw(amt);
						break;
					}
					else
					{
						System.out.println("Sorry Insufficient balance .Your current balance is Rs."+cs.getAccount().get(0).getAccbal());break;
					}
					
				}
				else
				{
					System.out.println("Sorry your pin number is incorrect...");break;
				}
				
			}
		}
	}
	
	public void balanceEnquiry()
	{
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		String custid=this.userid;
		System.out.println("Enter your pin no");
		String pass=sc.nextLine();
		ArrayList<Customer> list=DataClass.customer;
		//while(list==null)
		{
			for(Object ob:list)
			{
				Customer cs=(Customer)ob;
				if(custid.equals(cs.getUserid()))
				{	check=false;
					if(pass.equals(cs.getPassword()))
					{
						System.out.println("Which account balance you want 1.SavingsAccount 2.LoanAccount");
						int choice=sc.nextInt();sc.nextLine();
						if(choice==1)
							System.out.println("Your Account balance is Rs"+cs.getAccount().get(choice-1).getAccbal());
						else if(choice==2&&cs.getAccount().size()>1)
						{
							System.out.println("LoanAccNo      AccountBal     LoanID     InterestPerMonth  PrinciplePerMonth  TotalAmountPerMonth  No_Of_Months  Totalamount   LoanDate");
							for(int i=1;i<cs.getAccount().size();i++)
							{
								cs.getAccount().get(i).printLoanDetails();
							}
						}
						else if(choice==2&&cs.getAccount().size()==1){
							System.out.println("You dont have any loan....");
						}
						else
							System.out.println("Invalid input.....");
						check=false;
						break;
					}
					else
					{
						System.out.println("Sorry your pin number is incorrect...");check=false;
						break;
					}
				}
					
			}
			//list=(ArrayList)ois.readObject();
		}
		if(check==true)
			System.out.println("Sorry this account number is wrong..");
		
	}
	public void transferAmt()
	{
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		String user_id=this.getUserid();
		System.out.println("Enter your pin no");
		String pass=sc.nextLine();
		ArrayList<Customer> list=DataClass.customer;
		//while(list==null)
		{
			for(Object ob:list)
			{
				Customer cs=(Customer)ob;
				if(user_id.equals(cs.getUserid()))
				{	check=false;
					if(pass.equals(cs.getPassword()))
					{
						System.out.println("Enter the receiver Account number");
						long receiveracc=sc.nextLong();sc.nextLine();
						System.out.println("Re-Enter the receiver Account number");
						long receiveracc1=sc.nextLong();sc.nextLine();
						if(cs.getAccount().get(0).getAccno()==receiveracc)
						{
							System.out.println("Sorry you are trying to transfer amount to your own account...please check the receiver account number");
							check=false;break;
						}
						if(receiveracc==receiveracc1)
						{
							boolean bol=true;
							for(int i=0;i<list.size();i++)
							{
								Customer rec=(Customer)list.get(i);
								{
									if(rec.getAccount().get(0).getAccno()==receiveracc)
									{
										System.out.println("Enter the amount you want to transfer");
										double amount=sc.nextDouble();sc.nextLine();
										if(amount<=cs.getAccount().get(0).getAccbal())
										{
											cs.getAccount().get(0).withdraw(amount);
											//list.set(list.indexOf(cs),cs);
											rec.getAccount().get(0).deposit(amount);
											//list.set(i, rec);
											System.out.println("Amount Transfer successfully...");bol=false;check=false;break;
										}
										else
										{
											System.out.println("Sorry Insufficient Balance...");bol=false;break;
										}
									}
								}
							}
							if(bol==true)
							{
								System.out.println("Sorry...receiver account number is invalid...");
							}
							
						}
						else
						{
							System.out.println("Sorry...Two account numbers are mismatched...");
						}
						
						check=false;
						break;
						
					}
					else
					{
						System.out.println("Sorry your pin number is incorrect...");check=false;
						break;
					}
				}
					
			}
			//list=(ArrayList)ois.readObject();
		}
		if(check==true)
			System.out.println("Sorry this account number is wrong..");
	}
	public void changePassword()
	{
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		String custid=this.getUserid();
		System.out.println("Enter your Current pin number");
		String pass=sc.nextLine();
		ArrayList<Customer> list=DataClass.customer;
		//while(list==null)
		{
			for(Object ob:list)
			{
				Customer cs=(Customer)ob;
				if(cs.getUserid().equals(custid))
				{	
					check=false;
					if(cs.getPassword().equals(pass))
					{
						System.out.println("Enter your new pin");
						String pin1=sc.nextLine();
						System.out.println("Re-Enter your password");
						String pin2=sc.nextLine();
						if(pin1.equals(pin2))
						{
							cs.setPassword(pin2);
							System.out.println("Pin is successfully changed...");
						}
						else
						{
							System.out.println("OOPS...Both pins are not same...");
						}
						break;	
					}
					else
					{
						System.out.println("Sorry your pin number is incorrect...");
						break;
					}
				}
					
			}
			//list=(ArrayList)ois.readObject();
		}
		if(check==true)
			System.out.println("Sorry this account number is wrong..");
	}
	public void printLoanDetails()
	{
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		String custid=this.getUserid();
		System.out.println("Enter your Current pin number");
		String pass=sc.nextLine();
		ArrayList<Customer> list=DataClass.customer;
		//while(list==null)
		{
			boolean isloan=false;
			for(Object ob:list)
			{
				Customer cs=(Customer)ob;
				if(cs.getUserid().equals(custid))
				{	
					check=false;
					if(cs.getPassword().equals(pass))
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
						else if(isloan==false)
							System.out.println("this customer has no loan");
					}
					else
					{
						System.out.println("Sorry your pin number is incorrect...");
						break;
					}
				}
					
			}
			//list=(ArrayList)ois.readObject();
		}
		if(check==true)
			System.out.println("Sorry this account number is wrong..");
	}
	public void viewTransactionDetails()
	{
		boolean check=true;
		Scanner sc=new Scanner(System.in);
		String custid=this.getUserid();
		System.out.println("Enter your Current pin number");
		String pass=sc.nextLine();
		ArrayList<Customer> list=DataClass.customer;
		//while(list==null)
		{
			for(Object ob:list)
			{
				Customer cs=(Customer)ob;
				if(cs.getUserid().equals(custid))
				{	
					check=false;
					if(cs.getPassword().equals(pass))
					{
						System.out.println("Press 1.SavingsAccount Transaction 2.Loan Transaction 3.Both TransactionHistory");
						int num=sc.nextInt();sc.nextLine();
						if(num>=1&&num<=3)
						{
							int i=0;
							if(num==2)
								i=1;
							for(;i<cs.getAccount().size();i++)
							{
								System.out.println("Transaction History of AccNo="+cs.getAccount().get(i).getAccno());
								System.out.println("S.no  Last_balance     Withdraw_Amount  Deposit_Amount   Current_Balance  transaction_date");
								cs.getAccount().get(i).printTrans_History();
								if(num==1&&i==0)
									break;	
							}
							if((num==2||num==3)&&cs.getAccount().size()<=1)
							{
								System.out.println("You don't have any loan...");break;
							}
						}
						else
							System.out.println("invalid input...");break;
					}
					else
					{
						System.out.println("Sorry your pin number is incorrect...");
						break;
					}
				}
					
			}
			//list=(ArrayList)ois.readObject();
		}
		if(check==true)
			System.out.println("Sorry this account number is wrong..");
	}
	
	public void deposit()
	{
		ArrayList<Customer> list=DataClass.customer;
		Scanner sc=new Scanner(System.in);
		String custid=this.getUserid();
		boolean check=false;
		for(int i=0;i<list.size();i++)
		{
			Customer cs=list.get(i);
			if(custid.equals(cs.getUserid()))
			{	
				for(int j=1;j<cs.getAccount().size();j++)
				{
					System.out.println("The following is your Loan details...");
					this.printLoanDetails();
					System.out.println("Please Enter the above accountnumber which you want to deposit");
					long accno=sc.nextLong();sc.nextLine();
					if(accno==cs.getAccount().get(j).getAccno())
					{
						check=true;
						//18,19,20
						LoanAccount la=(LoanAccount)cs.getAccount().get(j);
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
						System.out.print(la.getMonthlyInterest()+la.getMonthlyprinciple());
						System.out.println();
						System.out.println("Press 1.To Proceed with the payment 2.To quit deposit");
						int choice=sc.nextInt();sc.nextLine();
						switch(choice)
						{
						case 1:if(cs.getAccount().get(0).getAccbal()>=(la.getMonthlyprinciple()+la.getMonthlyInterest()))
								{
									cs.getAccount().get(0).withdraw(Math.ceil(la.getMonthlyInterest()+la.getMonthlyprinciple()));
									cs.getAccount().get(j).deposit(Math.ceil(la.getMonthlyInterest()+la.getMonthlyprinciple()));
									
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
							cs.getAccount().remove(j);
						}
						else
						{
							System.out.println("Still your need to pay the loan for further "+la.getMonth()+" months");
							
						}
						break;
					}
				}
			}
		}
		if(check==false)
			System.out.println("Sorry this account number is not available...");	
	}
}
