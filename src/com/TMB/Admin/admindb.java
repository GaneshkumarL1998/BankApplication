package com.TMB.Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bankApp.MainClass;

public class admindb {
	
	public static Administrator selectAdmin(String userid)
	{
		
		Administrator ad=null;
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("select * from admindb where userid=?");
			ps.setString(1, userid);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ad=new Administrator(rs.getString(3),rs.getString(4), rs.getInt(1),rs.getString(2), rs.getLong(6),rs.getString(8),rs.getString(5), rs.getDouble(7));
			}
			return ad;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ad;
	}
	public static void updateAdmin(Administrator ad)
	{
		
		int rowsaffected=0;
		try {
			Connection conn=MainClass.conn;
			PreparedStatement ps=conn.prepareStatement("update admindb set userid=?,a_name=?,password=?,phoneno=?,dob=?,emailid=?,salary=? where empid=?");
			ps.setString(1, ad.getUserid());
			ps.setString(2,ad.getName());
			ps.setString(3, ad.getPassword());
			ps.setLong(4,ad.getMobileno());
			ps.setString(5,ad.getDOB());
			ps.setString(6,ad.getEmailid());
			ps.setDouble(7,ad.getSalary());
			ps.setInt(8,ad.getEmpid());
			rowsaffected=ps.executeUpdate();
			if(rowsaffected>0)
			{
				System.out.println("Successfully updated");
			}
			else {
				System.out.println("update failed");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
