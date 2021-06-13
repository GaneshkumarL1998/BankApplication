package com.TMB.Employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
public interface EmployeeInterface extends Serializable {
	EmployeeInterface login();
	void adduser();
	void removeuser();
	void updateuser();
	void searchusers();
	void changePassword();
	void viewAllUsers();
	void giveloan();
	void viewTransactionDetails();
	void viewLoanDetails();
	void deposit();
	void printLoanRate();
	void withdraw();
}
