package com.TMB.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import bankApp.MainClass;
public class CustomerDB {
	public static boolean insertCustomer(Customer cust)
	{
		 
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("INSERT INTO customerdb(custid,password,c_name,dob,emailid, phoneno)VALUES ( ?, ?, ?, ?, ?, ?)");
			
			ps.setString(1,cust.getUserid());
			ps.setString(2, cust.getPassword());
			ps.setString(3,cust.getName());
			ps.setString(4,cust.getDOB());
			ps.setString(5,cust.getEmailid());
			ps.setLong(6,cust.getMobileno());
			int rowsinserted=ps.executeUpdate();
			
			if(rowsinserted>0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	public static Customer selectCustomer(String custid)
	{
		Customer cust=null;
		
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("select * from customerdb where custid=?");
			ps.setString(1, custid);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				//String userid, String password, String name, long mobileno, String dOB, String emailid,ArrayList<Long>acc
				cust=new Customer(rs.getString(1),rs.getString(2),rs.getString(3),rs.getLong(6),rs.getString(4),rs.getString(5));
				
			}
			
			return cust;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public static void searchCustomer(String what, long arg)
	{
		String query="select * from customerdb where "+what+"=?";
		if(what.equals("accno"))
		{
			query="select * from customerdb where "+what+"[1]=?";
		}
		Customer cust=null;
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setLong(1, arg);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				//String userid, String password, int empid, String name, long mobileno, String DOB, String emailid,double salary
				cust=new Customer(rs.getString(1),rs.getString(2),rs.getString(3),rs.getLong(6),rs.getString(4),rs.getString(5));
				cust.printCustomer();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void searchCustomer(String what,String arg)
	{
		Customer cust=null;
		
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("select * from customerdb where "+what+"=?");
			ps.setString(1, arg);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				//String userid, String password, int empid, String name, long mobileno, String DOB, String emailid,double salary
				cust=new Customer(rs.getString(1),rs.getString(2),rs.getString(3),rs.getLong(6),rs.getString(4),rs.getString(5));
				cust.printCustomer();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void selectAllCustomer()
	{
		Customer cust=null;
		
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("select * from customerdb");
			ResultSet rs=ps.executeQuery();
			System.out.println("CustomerId     AccountNumber  AccounBalance    PinNumber      CustomerName   DateOfBirth  MobileNumber   emailid                       ");
			while(rs.next())
			{
				//String userid, String password, int empid, String name, long mobileno, String DOB, String emailid,double salary
				cust=new Customer(rs.getString(1),rs.getString(2),rs.getString(3),rs.getLong(6),rs.getString(4),rs.getString(5));
				cust.printCustomer();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void updateCustomer(Customer emp)
	{
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("update customerdb set  password=?,c_name=?,dob=?,emailid=?, phoneno=?where custid=?");
			ps.setString(1,emp.getPassword());
			ps.setString(2, emp.getName());
			ps.setString(3, emp.getDOB());
			ps.setString(4,emp.getEmailid());
			ps.setLong(5,emp.getMobileno());
			ps.setString(6,emp.getUserid());
			int rowsinserted=ps.executeUpdate();
			if(rowsinserted>0)
				System.out.println("Successfully updated");
			else
				System.out.println("Sorry not updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void removeCustomer(String custid)
	{
		Customer cust=null;
		
		int rowsinserted=0;
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("delete from customerdb where custid=?");
			ps.setString(1, custid);
			rowsinserted=ps.executeUpdate();
			if(rowsinserted>0)
				System.out.println("Successfully removed");
			else
				System.out.println("Sorry name and accno mismatched");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
