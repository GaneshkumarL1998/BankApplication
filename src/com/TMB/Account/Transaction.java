package com.TMB.Account;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction{
	static DateFormat dateformat=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	private double current_bal;
	private double withdraw_amt;
	private double deposit_amt;
	private double rem_bal;
	private String transaction_date;
	public Transaction(double current_bal, double withdraw_amt, double deposit_amt, double rem_bal,
			Date transaction_date) {
		
		super();
		this.current_bal =(double)Math.round(100*current_bal)/100;
		this.withdraw_amt = (double)Math.round(100*withdraw_amt)/100;
		this.deposit_amt = (double)Math.round(100*deposit_amt)/100;
		this.rem_bal = (double)Math.round(100*rem_bal)/100;
		this.transaction_date =dateformat.format(transaction_date);
	}
	public double getCurrent_bal() {
		return current_bal;
	}
	public void setCurrent_bal(double current_bal) {
		this.current_bal = current_bal;
	}
	public double getWithdraw_amt() {
		return withdraw_amt;
	}
	public void setWithdraw_amt(double withdraw_amt) {
		this.withdraw_amt = withdraw_amt;
	}
	public double getDeposit_amt() {
		return deposit_amt;
	}
	public void setDeposit_amt(double deposit_amt) {
		this.deposit_amt = deposit_amt;
	}
	public double getRem_bal() {
		return rem_bal;
	}
	public void setRem_bal(double rem_bal) {
		this.rem_bal = rem_bal;
	}
	public String getTransaction_date() {
		
		return transaction_date;
	}
	public void setTransaction_date(Date transaction_date) {
		String str=dateformat.format(transaction_date);
		this.transaction_date = str;
	}
	public void printTrans_History() {
		//System.out.println("transaction details account AccNo="+this.getAccno());
		//System.out.println("S.no  Last_balance     Withdraw_Amount  Deposit_Amount   Current_Balance  transaction_date");
			
			String str=String.valueOf(getCurrent_bal());
			System.out.print(str);
			for(int k=str.length();k<17;k++)
				System.out.print(" ");
			str=String.valueOf(getWithdraw_amt());
			System.out.print(str);
			for(int k=str.length();k<17;k++)
				System.out.print(" ");
			str=String.valueOf(getDeposit_amt());
			System.out.print(str);
			for(int k=str.length();k<17;k++)
				System.out.print(" ");
			str=String.valueOf(getRem_bal());
			System.out.print(str);
			for(int k=str.length();k<17;k++)
				System.out.print(" ");
			System.out.print(getTransaction_date());
			System.out.println();
		}	
	
}
