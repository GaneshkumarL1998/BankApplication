package com.TMB.datafiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class AccountDataClass implements Serializable{
	public static long Savings_Acc_no;
	public static long Loan_Acc_no;
	public static int empid;
	public static long customerid;
	public static int houseloanno;
	public static int bikeloanno;
	public static int carloanno;
	public static int personalloanno;
	public static File fi=new File("C:\\Users\\GANESH\\Desktop\\ZohoBank\\Accountdata.txt");
	public static void readfile() throws IOException
	{
		if(fi.exists()==false)
		{
			fi.createNewFile();
			Savings_Acc_no=10001;
			Loan_Acc_no=100200301;
			empid=2000;
			customerid=30001;
			houseloanno=1;
			bikeloanno=1;
			carloanno=1;
			personalloanno=1;
		}
		else
		{
			FileReader fr=new FileReader(fi);
			BufferedReader br=new BufferedReader(fr);
			Savings_Acc_no=Long.parseLong(br.readLine());
			Loan_Acc_no=Long.parseLong(br.readLine());
			empid=Integer.parseInt(br.readLine());
			customerid=Long.parseLong(br.readLine());
			houseloanno=Integer.parseInt(br.readLine());
			bikeloanno=Integer.parseInt(br.readLine());
			carloanno=Integer.parseInt(br.readLine());
			personalloanno=Integer.parseInt(br.readLine());
		}
	}
	public static void writefile() throws IOException
	{
		FileWriter fw=new FileWriter(fi);
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(String.valueOf(Savings_Acc_no)+"\n");
		bw.write(""+Loan_Acc_no+"\n");
		bw.write(""+empid+"\n");
		bw.write(""+customerid+"\n");
		bw.write(""+houseloanno+"\n");
		bw.write(""+bikeloanno+"\n");
		bw.write(""+carloanno+"\n");
		bw.write(""+personalloanno+"\n");
		bw.close();
		fw.close();		
	}
}
