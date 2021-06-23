package bankApp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.TMB.Admin.AdminInterface;
import com.TMB.Customer.CustomerInterface;
import com.TMB.Employee.EmployeeInterface;
import com.TMB.datafiles.AccountDataClass;


public class MainClass {
	public static Connection conn;
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		try {
			Class.forName("org.postgresql.Driver");
			conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/bank","postgres","Ganeshkumar@07");
			AccountDataClass.readfile();
			Scanner input=new Scanner(System.in);
			System.out.println("*****Welcome to TAMILNAD MERCANTILE BANK*****");		 
			while(true)
			{
				System.out.println("1.Admin 2.Employee 3.Customer 0.To Close this application");
				int num=input.nextInt();input.nextLine();
				Bank.setEmployee(num);
				if(num==0)
				{
					System.out.println("This Application is closed successfully..");
					break;
				}
				else if(num==1)
				{
					AdminInterface ei=Bank.getAdminInterface().login();
					if(ei!=null)	
					{
						boolean enter=true;
						while(enter)
						{
							System.out.println("Press 0.logout 1.addUser 2.RemoveUser 3.UpdateUser 4.searchUser 5.updateAdminDetails 6.PrintAdminDetails  7.ViewAllUsers ");
							int choice=input.nextInt();input.nextLine();
							switch(choice)
							{
							case 1:ei.adduser();;break;
							case 2:ei.removeuser();break;
							case 3:ei.updateuser();break;
							case 4:ei.searchusers();break;
							case 5:ei.updateAdminDetails();break;
							case 6:ei.printAdminDetails();break;
							case 7:ei.viewAllUsers();break;						
							case 0:System.out.println("You are loggedout...");
									Bank.setAdminInterface(null);enter=false;break;
							default:System.out.println("Sorry invalid input");break;
							}
						}
					}
				}
				else if(num==2)
				{
					EmployeeInterface ei=Bank.getEmployeeInterface().login();
					if(ei!=null)	
					{
						boolean enter=true;
						while(enter)
						{
							System.out.println("Press 0.logout 1.addUser 2.RemoveUser 3.UpdateUser 4.searchUser 5.ChangePassword 6.ViewAllUsers \n"
									+"7.GiveLoan 8.ViewTransactionDetails 9.ViewLoanDetails 10.Deposit 11.viewLoanRates 12.Withdraw");
							int choice=input.nextInt();input.nextLine();
							switch(choice)
							{
							case 1:ei.adduser();;break;
							case 2:ei.removeuser();break;
							case 3:ei.updateuser();break;
							case 4:ei.searchusers();break;
							case 5:ei.changePassword();enter=false;break;
							case 6:ei.viewAllUsers();break;
							case 7:ei.giveloan();break;
							case 8:ei.viewTransactionDetails();break;
							case 9:ei.viewLoanDetails();break;
							case 10:ei.deposit();break;
							case 11:ei.printLoanRate();break;
							case 12:ei.withdraw();break;
							case 0:System.out.println("You are loggedout...");Bank.setEmployeeInterface(null);enter=false;break;
							default:System.out.println("Sorry invalid input");break;
							}
						}
					}
				}
				else if(num==3)
				{
					CustomerInterface ci=Bank.getCustomerInterface().login();
					if(ci!=null)
					{
						boolean enter=true;
						while(enter)
						{
							System.out.println("Press 0.logout 1.WithDraw 2.balanceEnquiry 3.AccountToAccount transaction 4.Change PinNumber 5.PrintLoanDetails 6.printTransactionDetails 7.deposit");
							int choice=input.nextInt();input.nextLine();
							switch(choice)
							{
							case 1:ci.withdraw();break;
							case 2:ci.balanceEnquiry();break;
							case 3:ci.transferAmt();break;
							case 4:ci.changePassword();enter=false;break;
							case 5:ci.printLoanDetails();break;
							case 6:ci.viewTransactionDetails();break;
							case 7:ci.deposit();break;
							case 0:System.out.println("You are loggedout...");Bank.setCustomerInterface(null);enter=false;break;
							default:System.out.println("Sorry invalid input");break;
							}
						}
					}	
				}
				else
					System.out.println("Sorry invalid input....");
			}
			input.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
		finally {
			try {
				AccountDataClass.writefile();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
