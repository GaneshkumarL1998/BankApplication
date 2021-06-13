package com.TMB.Account;

import java.io.Serializable;

public class BikeLoan implements Loan,Serializable{
	public double getLoanRate() {
		return 5.5;
	}
}
