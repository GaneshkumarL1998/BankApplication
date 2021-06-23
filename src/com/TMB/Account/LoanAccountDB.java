package com.TMB.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import bankApp.MainClass;

public class LoanAccountDB {
	static DateFormat date=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	public static void insertAccount(LoanAccount acc)
	{
		 
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("INSERT INTO loanaccdb (accno,accbal,loanid,month_int,month_princ,month_pay,month,totalpay,loandate,custid)VALUES (?, ?,?, ?,?, ?,?, ?,?, ?)");
			ps.setLong(1,acc.getAccno());
			ps.setDouble(2,acc.getAccbal());
			ps.setString(3, acc.getLoanID());
			ps.setDouble(4,acc.getMonthlyInterest());
			ps.setDouble(5,acc.getMonthlyprinciple());
			ps.setDouble(6,acc.getMonthlytotalpay());
			ps.setDouble(7,acc.getMonth());
			ps.setDouble(8,acc.getTotalpay());
			ps.setString(9,acc.getLoan_Sanction_date());
			ps.setString(10, acc.custid);
			int rowsinserted=ps.executeUpdate();
			Transaction ts=new Transaction(0,acc.getAccbal(), 0, acc.getAccbal(), new Date());
			TransactionDB.insertTransactionDetails(ts, acc.getAccno());
			//TransactionDB.insertTransactionDetails(acc.getTransaction(), acc.getAccno());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static Account selectAccount(long accno)
	{
		Account acc=null;
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("select * from loanaccdb where accno=?");
			ps.setLong(1, accno);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				acc=new LoanAccount(rs.getString(10),rs.getLong(1),rs.getDouble(2),rs.getString(3),rs.getDouble(4), rs.getDouble(5),rs.getDouble(6),rs.getDouble(7),rs.getDouble(8),date.parse(rs.getString(9)));
			}
			return acc;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static ArrayList<Account> selectAccount(String custid)
	{
		ArrayList<Account >acc=new ArrayList<Account>();
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("select * from loanaccdb where custid=?");
			ps.setString(1, custid);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				acc.add(new LoanAccount(rs.getString(10),rs.getLong(1),rs.getDouble(2),rs.getString(3),rs.getDouble(4), rs.getDouble(5),rs.getDouble(6),rs.getDouble(7),rs.getDouble(8),date.parse(rs.getString(9))));
			}
			return acc;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return acc;
	}
	public static boolean updateAccount(LoanAccount acc)
	{
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("update loanaccdb set accbal=?,month=?,totalpay=? where accno=?");
			ps.setDouble(1, acc.getAccbal());
			ps.setDouble(2,acc.getMonth());
			ps.setDouble(3,acc.getTotalpay());
			ps.setLong(4, acc.getAccno());
			//TransactionDB.insertTransactionDetails(acc.getTransaction(),acc.getAccno());
			int rowsaffected=ps.executeUpdate();
			if(rowsaffected>0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static boolean deleteAccount(long accno)
	{
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("delete from loanaccdb where accno=?");
			ps.setDouble(1,accno);
			TransactionDB.deleteTransaction(accno);
			int rowsaffected=ps.executeUpdate();
			if(rowsaffected>0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
