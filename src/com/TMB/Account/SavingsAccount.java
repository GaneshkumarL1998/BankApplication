package com.TMB.Account;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SavingsAccount extends Account implements Serializable{
	private long accno;
	private double accbal;
	private List<Transaction> trans_history=new LinkedList<Transaction>();
	public SavingsAccount(long accno,double accbal) {
		super();
		this.accno=accno;
		this.accbal=(double)Math.round(accbal*100)/100;
		this.trans_history.add(new Transaction(0,0,accbal,accbal,new Date()));
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
	
	public List<Transaction> getTrans_history() {
		return trans_history;
	}
	public void printLoanDetails() {
		//dummy method...
	}
	public void withdraw(double amt)
	{
		double cur_bal=this.getAccbal();
		accbal-=(double)Math.round(amt*100)/100;
		accbal=(double)Math.round(accbal*100)/100;
		this.getTrans_history().add(new Transaction(cur_bal,amt,0,accbal,new Date()));
		System.out.println("Successfully withdrawed and your Savingsaccount balance =Rs"+this.getAccbal());
	}
	public void deposit(double amt)
	{
		double cur_bal=this.getAccbal();
		accbal+=(double)Math.round(amt*100)/100;
		accbal=(double)Math.round(accbal*100)/100;
		this.getTrans_history().add(new Transaction(cur_bal,0,amt,accbal,new Date()));
		System.out.println("Successfully deposited and the deposited ammount=Rs"+amt);
	}
	
}
