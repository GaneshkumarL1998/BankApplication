package com.TMB.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import bankApp.MainClass;

public class EmployeeDB {
	public static boolean insertelement(Employee emp)
	{
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("INSERT INTO empdb(empid, e_name, userid, password,emailid, phoneno, salary,dob)VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1,emp.getEmpid());
			ps.setString(2, emp.getName());
			ps.setString(3,emp.getUserid());
			ps.setString(4,emp.getPassword());
			ps.setString(5,emp.getEmailid());
			ps.setLong(6,emp.getMobileno());
			ps.setDouble(7, emp.getSalary());
			ps.setString(8, emp.getDOB());
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
	public static Employee selectEmployee(String userid)
	{
		Employee emp=null;
		
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("select * from empdb where userid=?");
			ps.setString(1, userid);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				//String userid, String password, int empid, String name, long mobileno, String DOB, String emailid,double salary
				emp=new Employee(rs.getString(3),rs.getString(4),rs.getInt(1),rs.getString(2),rs.getLong(6),rs.getString(8),rs.getString(5),rs.getDouble(7));
			}
			
			return emp;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static Employee selectEmployee(int empid)
	{
		Employee emp=null;
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("select * from empdb where empid=?");
			ps.setInt(1, empid);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				//String userid, String password, int empid, String name, long mobileno, String DOB, String emailid,double salary
				emp=new Employee(rs.getString(3),rs.getString(4),rs.getInt(1),rs.getString(2),rs.getLong(6),rs.getString(8),rs.getString(5),rs.getDouble(7));
			}
			
			return emp;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public static void searchEmployee(int empid)
	{
		Employee emp=null;
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("select * from empdb where empid=?");
			ps.setInt(1, empid);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				//String userid, String password, int empid, String name, long mobileno, String DOB, String emailid,double salary
				emp=new Employee(rs.getString(3),rs.getString(4),rs.getInt(1),rs.getString(2),rs.getLong(6),rs.getString(8),rs.getString(5),rs.getDouble(7));
				emp.printEmployee();
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void searchEmployee(long mobileno)
	{
		Employee emp=null;
		
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("select * from empdb where phoneno=?");
			ps.setLong(1, mobileno);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				//String userid, String password, int empid, String name, long mobileno, String DOB, String emailid,double salary
				emp=new Employee(rs.getString(3),rs.getString(4),rs.getInt(1),rs.getString(2),rs.getLong(6),rs.getString(8),rs.getString(5),rs.getDouble(7));
				emp.printEmployee();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void searchEmployee(String what,String arg)
	{
		Employee emp=null;
		
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("select * from empdb where "+what+"=?");
			ps.setString(1, arg);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				//String userid, String password, int empid, String name, long mobileno, String DOB, String emailid,double salary
				emp=new Employee(rs.getString(3),rs.getString(4),rs.getInt(1),rs.getString(2),rs.getLong(6),rs.getString(8),rs.getString(5),rs.getDouble(7));
				emp.printEmployee();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void selectAllEmployee()
	{
		Employee emp=null;
		
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("select * from empdb");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				//String userid, String password, int empid, String name, long mobileno, String DOB, String emailid,double salary
				emp=new Employee(rs.getString(3),rs.getString(4),rs.getInt(1),rs.getString(2),rs.getLong(6),rs.getString(8),rs.getString(5),rs.getDouble(7));
				emp.printEmployee();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void updateEmployee(Employee emp)
	{
		
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("update empdb set e_name=?, userid=?, password=?,emailid=?, phoneno=?, salary=?,dob=? where empid=?");
			
			ps.setString(1, emp.getName());
			ps.setString(2,emp.getUserid());
			ps.setString(3,emp.getPassword());
			ps.setString(4,emp.getEmailid());
			ps.setLong(5,emp.getMobileno());
			ps.setDouble(6, emp.getSalary());
			ps.setString(7, emp.getDOB());
			ps.setInt(8,emp.getEmpid());
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
	public static void removeEmployee(int empid)
	{
		Employee emp=null;
		
		int rowsinserted=0;
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("delete from empdb where empid=?");
			ps.setInt(1, empid);
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