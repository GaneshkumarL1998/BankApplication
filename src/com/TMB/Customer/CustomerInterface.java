package com.TMB.Customer;

import java.io.Serializable;

public interface CustomerInterface extends Serializable{
	 CustomerInterface login();
	 void withdraw();
	 void balanceEnquiry();
	 void transferAmt();
	 void changePassword();
	 void printLoanDetails();
	 void viewTransactionDetails();
	 void deposit();
}
