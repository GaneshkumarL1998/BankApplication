package com.TMB.Account;

import java.util.Date;

public class SavingsAccount extends Account {
	private long accno;
	private double accbal;
	public String custid;
	private Transaction ts;
	public SavingsAccount(long accno,double accbal ,String custid) {
		super();
		this.accno=accno;
		this.accbal=(double)Math.round(accbal*100)/100;
		this.custid=custid;
	}	
	
	public Transaction getTransaction()
	{
		return ts;
	}
	public long getAccno() {
		return accno;
	}

	public void setAccno(long accno) {
		this.accno = accno;
	}

	public double getAccbal() {
		return accbal;
	}
	public void setAccbal(double accbal) {
		this.accbal = accbal;
	}
	public String getCustid()
	{
		return custid;
	}
	public void printLoanDetails() {
		//dummy method...
	}
	public void withdraw(double amt)
	{
		double cur_bal=this.getAccbal();
		accbal-=(double)Math.round(amt*100)/100;
		accbal=(double)Math.round(accbal*100)/100;
		TransactionDB.insertTransactionDetails((new Transaction(cur_bal,0,amt,accbal,new Date())),accno);
		System.out.println("Successfully withdrawed and your Savingsaccount balance =Rs"+this.getAccbal());
	}
	public void deposit(double amt)
	{
		double cur_bal=this.getAccbal();
		accbal+=(double)Math.round(amt*100)/100;
		accbal=(double)Math.round(accbal*100)/100;
		TransactionDB.insertTransactionDetails((new Transaction(cur_bal,amt,0,accbal,new Date())),accno);
		System.out.println("Successfully deposited and the deposited ammount=Rs"+amt);
	}
	
}
