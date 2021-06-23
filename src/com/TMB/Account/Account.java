package com.TMB.Account;


public abstract class Account{
	public abstract void setAccno(long accno);
	public abstract long getAccno();
	public abstract double getAccbal();
	public abstract void setAccbal(double accbal);
	public abstract void printLoanDetails();
	public abstract void deposit(double amt);
	public abstract void withdraw(double amt);
	public abstract String getCustid();
	
	
}
