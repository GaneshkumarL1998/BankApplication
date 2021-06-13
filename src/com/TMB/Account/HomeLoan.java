package com.TMB.Account;

import java.io.Serializable;

public class HomeLoan implements Loan,Serializable{
	public double getLoanRate() {
		return 7.5;
	}
}

