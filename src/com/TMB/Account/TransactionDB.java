package com.TMB.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.TMB.Employee.Employee;

import bankApp.MainClass;

public class TransactionDB {
	static DateFormat dateformat=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	public static void printTransactionDetails(long accno)
	{
		Transaction tran=null;
		
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("select * from transactiondb where accno=?");
			ps.setLong(1,accno);
			ResultSet rs=ps.executeQuery();
			//System.out.println("Last_balance     Withdraw_Amount  Deposit_Amount   Current_Balance  transaction_date");
			while(rs.next())
			{
				//String userid, String password, int empid, String name, long mobileno, String DOB, String emailid,double salary
				tran=new Transaction(rs.getDouble(2),rs.getDouble(3),rs.getDouble(4),rs.getDouble(5),dateformat.parse(rs.getString(6)));
				tran.printTrans_History();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void insertTransactionDetails(Transaction trans,long accno)
	{
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("INSERT INTO transactiondb(accno,oldbalance,withdrawamt,depositamt,newbalance,trans_date)VALUES (?, ?, ?, ?, ?, ?)");
			ps.setLong(1,accno);
			ps.setDouble(2,trans.getCurrent_bal());
			ps.setDouble(3,trans.getWithdraw_amt());
			ps.setDouble(4,trans.getDeposit_amt());
			ps.setDouble(5,trans.getRem_bal());
			ps.setString(6,trans.getTransaction_date());
			int rowsaffected=ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void deleteTransaction(long accno)
	{
		Transaction tran=null;
		
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("delete from transactiondb where accno=?");
			ps.setLong(1,accno);
			int rowsaffected=ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
