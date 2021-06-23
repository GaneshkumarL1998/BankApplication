package com.TMB.Account;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoanAccount extends Account{
	static DateFormat dateformat=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	private long accno;
	private double accbal;
	private String loanID;
	private double monthlyInterest;
	private double monthlyprinciple;
	private double monthlytotalpay;
	private double month;
	private double totalpay;
	private String loan_Sanction_date;
	public String custid;
	private Transaction ts;
	public LoanAccount(String custid,long accno,double accbal,String loanID
			,double monthlyInterest,double monthlyprinciple,double monthlytotal,double month,double totalpay,Date date)
	{
		this.custid=custid;
		this.accno=accno;
		this.accbal=(double)Math.round(accbal*100)/100;
		this.loanID=loanID;
		this.monthlyInterest=(double)Math.round(monthlyInterest*100)/100;
		this.monthlyprinciple=(double)Math.round(monthlyprinciple*100)/100;
		this.monthlytotalpay=monthlytotal;
		this.month=month;
		this.totalpay=(double)Math.round(totalpay*100)/100;
		this.loan_Sanction_date=dateformat.format(date);
		
	}
	public Transaction getTransaction()
	{
		return ts;
	}
	public String getCustid()
	{
		return custid;
	}
	public double getMonth() {
		return month;
	}
	public void setMonth(double month) {
		this.month=month;
	}
	public String getLoan_Sanction_date() {
		return loan_Sanction_date;
	}
	public void setLoan_Sanction_date(String loan_Sanction_date) {
		this.loan_Sanction_date = loan_Sanction_date;
	}
	public void setAccno(long accno) {
		this.accno=accno;	
	}
	public long getAccno() {
		return accno;
	}
	public double getAccbal() {
		return accbal;
	}
	public void setAccbal(double accbal) {
		this.accbal = accbal;
	}
	
	public double getMonthlytotalpay() {
		return monthlytotalpay;
	}
	public void setMonthlytotalpay(double monthlytotalpay) {
		this.monthlytotalpay = monthlytotalpay;
	}
	public String getLoanID() {
		return loanID;
	}
	public void setLoanID(String loanID) {
		this.loanID = loanID;
	}
	public double getMonthlyInterest() {
		return monthlyInterest;
	}
	public void setMonthlyInterest(double monthlyInterest) {
		this.monthlyInterest = monthlyInterest;
	}
	public double getMonthlyprinciple() {
		return monthlyprinciple;
	}
	public void setMonthlyprinciple(double monthlyprinciple) {
		this.monthlyprinciple = monthlyprinciple;
	}
	public double getTotalpay() {
		return totalpay;
	}
	public void setTotalpay(double totalpay) {
		this.totalpay = totalpay;
	}

	public void withdraw(double amt)
	{
		
	}
	public void deposit(double amt)
	{
		totalpay-=amt;
		accbal-=(double)Math.round(monthlyprinciple*100)/100;
		accbal=(double)Math.round(accbal*100)/100;
		month-=1;
		TransactionDB.insertTransactionDetails(new Transaction(accbal+amt,0,amt, accbal,new Date()), accno);
		System.out.println("Loan for this month is paid successfully");
	}

	public void printLoanDetails()
	{
		//15,15,13,17,18,14,14
		//System.out.println("LoanAccNo      AccountBal     LoanID     InterestPerMonth  PrinciplePerMonth  TotalAmountPerMonth  No_Of_Months  Totalamount   LoanDate");

		System.out.print(accno);
		String str=String.valueOf(accno);
		for(int i=str.length();i<15;i++)
			System.out.print(" ");
		System.out.print(accbal);
		str=String.valueOf(accbal);
		for(int i=str.length();i<15;i++)
			System.out.print(" ");
		System.out.print(loanID);
		for(int i=loanID.length();i<11;i++)
			System.out.print(" ");
		System.out.print(monthlyInterest);
		str=String.valueOf(monthlyInterest);
		for(int i=str.length();i<18;i++)
			System.out.print(" ");
		System.out.print(monthlyprinciple);
		str=String.valueOf(monthlyprinciple);
		for(int i=str.length();i<19;i++)
			System.out.print(" ");
		System.out.print(monthlytotalpay);
		str=String.valueOf(monthlytotalpay);
		for(int i=str.length();i<21;i++)
			System.out.print(" ");
		System.out.print(month);
		str=String.valueOf(month);
		for(int i=str.length();i<14;i++)
			System.out.print(" ");
		System.out.print(totalpay);
		str=String.valueOf(totalpay);
		for(int i=str.length();i<14;i++)
			System.out.print(" ");
		System.out.print(loan_Sanction_date);
		System.out.println();
	}
	
}
