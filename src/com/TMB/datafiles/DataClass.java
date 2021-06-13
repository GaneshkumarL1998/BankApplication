package com.TMB.datafiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.TMB.Admin.Administrator;
import com.TMB.Customer.Customer;
import com.TMB.Employee.Employee;

public class DataClass implements Serializable{
	public static ArrayList<Administrator> admin;
	public static ArrayList<Employee> employee;
	public static ArrayList<Customer> customer;
	public static File fi=new File("C:\\Users\\GANESH\\Desktop\\ZohoBank\\datafile.ser");
	public static void readfile() throws IOException, ClassNotFoundException
	{
		
		if(fi.exists()==false)
		{
			//String userid, String password, int empid, String name, long mobileno, String DOB,String emailid,double salary
			fi.createNewFile();
			admin=new ArrayList<Administrator>();
			admin.add(new Administrator("admin123","12345",10001,"Ganesh Kumar",9976643624l,"10/11/1996","Admin123@gmail.com",45000));
			employee=new ArrayList<Employee>();
			customer=new ArrayList<Customer>();
			//customer.clear();
			
		}
		else
		{
			FileInputStream fis=new FileInputStream(fi);
			ObjectInputStream ois=new ObjectInputStream(fis);
			admin=(ArrayList)ois.readObject();
			employee=(ArrayList)ois.readObject();
			customer=(ArrayList)ois.readObject();
			//customer=new ArrayList<Customer>();
		}
	}
	public static  void writefile() throws IOException
	{
		FileOutputStream fos=new FileOutputStream(fi);
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		oos.writeObject(admin);
		oos.writeObject(employee);
		oos.writeObject(customer);
		fos.close();oos.close();
	}
	
}
