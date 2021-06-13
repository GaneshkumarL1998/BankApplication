package com.TMB.Account;

import java.io.Serializable;

public class LoanFactory implements Serializable
{
	public static Loan selectLoan(int n)
	{
		if(n==1)
			return new HomeLoan();
		else if(n==2)
			return new CarLoan();
		else if(n==3)
			return new BikeLoan();
		else if(n==4)
			return new PersonalLoan();
		else
		{
			System.out.println("Invalid input...");return null;
		}
			
	}
}
