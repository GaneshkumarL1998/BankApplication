package com.TMB.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.TMB.Employee.Employee;

import bankApp.MainClass;

public class SavingsAccountDB {
	public static void insertAccount(SavingsAccount acc)
	{
		 
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("INSERT INTO savingsacc (accno,accbal,custid)VALUES (?, ?,?)");
			ps.setLong(1,acc.getAccno());
			ps.setDouble(2,acc.getAccbal());
			ps.setString(3, acc.custid);
			int rowsinserted=ps.executeUpdate();
			Transaction ts=new Transaction(0, 0,acc.getAccbal(), acc.getAccbal(), new Date());
			TransactionDB.insertTransactionDetails(ts, acc.getAccno());
			//TransactionDB.insertTransactionDetails(acc.getTransaction(), acc.getAccno());
			if(rowsinserted>0)
				System.out.println("Inserted successfully");
			else
				System.out.println("not inserted");
			
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
			PreparedStatement ps=conn.prepareStatement("select * from savingsacc where accno=?");
			ps.setLong(1, accno);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				acc=new SavingsAccount(rs.getLong(1),rs.getDouble(2),rs.getString(3));
			}
			return acc;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static Account selectAccount(String custid)
	{
		Account acc=null;
		
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("select * from savingsacc where custid=?");
			ps.setString(1, custid);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				acc=new SavingsAccount(rs.getLong(1),rs.getDouble(2),rs.getString(3));
			}
			return acc;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static boolean updateAccount(SavingsAccount acc)
	{
		
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("update savingsacc set accbal=? where accno=?");
			ps.setDouble(1, acc.getAccbal());
			ps.setLong(2, acc.getAccno());
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
			PreparedStatement ps=conn.prepareStatement("delete from savingsacc where accno=?");
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
