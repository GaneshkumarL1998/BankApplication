package com.TMB.Account;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LoanAccount extends Account implements Serializable{
	static DateFormat dateformat=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	private Loan loanbehaviour;
	private long accno;
	private double accbal;
	private String loanID;
	private double monthlyInterest;
	private double monthlyprinciple;
	private double monthlytotalpay;
	private double month;
	private double totalpay;
	private String loan_Sanction_date;
	private List<Transaction> trans_history=new LinkedList<Transaction>();
	public LoanAccount(Loan loanbehaviour,long accno,double accbal,String loanID
			,double monthlyInterest,double monthlyprinciple,double year,double totalpay,Date date)
	{
		this.loanbehaviour=loanbehaviour;
		this.accno=accno;
		this.accbal=(double)Math.round(accbal*100)/100;
		this.loanID=loanID;
		this.monthlyInterest=(double)Math.round(monthlyInterest*100)/100;
		this.monthlyprinciple=(double)Math.round(monthlyprinciple*100)/100;
		this.monthlytotalpay=(double)Math.round((monthlyInterest+monthlyprinciple)*100)/100;
		this.month=year*12;
		this.totalpay=(double)Math.round(totalpay*100)/100;
		this.loan_Sanction_date=dateformat.format(date);
		trans_history.add(new Transaction(0,accbal, 0, accbal, date));
	}
	public double getMonth() {
		return month;
	}
	public void setMonth(double year) {
		this.month=year*12;
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
	public List<Transaction> getTrans_history() {
		return trans_history;
	}
	public void setTrans_history(List<Transaction> trans_history) {
		this.trans_history = trans_history;
	}
	public Loan getLoanbehaviour() {
		return loanbehaviour;
	}
	public void setLoanbehaviour(Loan loanbehaviour) {
		this.loanbehaviour = loanbehaviour;
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
		trans_history.add(new Transaction(accbal+amt,0,amt, accbal,new Date()));
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
