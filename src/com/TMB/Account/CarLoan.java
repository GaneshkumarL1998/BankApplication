package com.TMB.Account;

import java.io.Serializable;

public class CarLoan implements Loan,Serializable{
	public double getLoanRate() {
		return 8.5;
	}
}
