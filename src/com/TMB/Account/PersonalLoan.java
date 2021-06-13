package com.TMB.Account;

import java.io.Serializable;

public class PersonalLoan  implements Loan,Serializable {
	public double getLoanRate() {
		return 10.0;
	}
}
