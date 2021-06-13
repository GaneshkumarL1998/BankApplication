package com.TMB.Account;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public abstract class Account implements Serializable{
	public abstract void setAccno(long accno);
	public abstract long getAccno();
	public abstract double getAccbal();
	public abstract void setAccbal(double accbal);
	public abstract List<Transaction> getTrans_history();
	public abstract void printLoanDetails();
	public abstract void deposit(double amt);
	public abstract void withdraw(double amt);
	
	
	public void printTrans_History() {
		//System.out.println("transaction details account AccNo="+this.getAccno());
		//System.out.println("S.no  Last_balance     Withdraw_Amount  Deposit_Amount   Current_Balance  transaction_date");
		for(int i=0;i<this.getTrans_history().size();i++)
		{ 
			Transaction ts=this.getTrans_history().get(i);
			if(i<9)
				System.out.print((i+1)+"     ");
			else
				System.out.print((i+1)+"    ");
			String str=String.valueOf(ts.getCurrent_bal());
			System.out.print(str);
			for(int k=str.length();k<17;k++)
				System.out.print(" ");
			str=String.valueOf(ts.getWithdraw_amt());
			System.out.print(str);
			for(int k=str.length();k<17;k++)
				System.out.print(" ");
			str=String.valueOf(ts.getDeposit_amt());
			System.out.print(str);
			for(int k=str.length();k<17;k++)
				System.out.print(" ");
			str=String.valueOf(ts.getRem_bal());
			System.out.print(str);
			for(int k=str.length();k<17;k++)
				System.out.print(" ");
			System.out.print(ts.getTransaction_date());
			System.out.println();
		}
	}
}
