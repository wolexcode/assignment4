package com.meritamerica.assignment4;

public class WithdrawTransaction extends Transaction {
	WithdrawTransaction(BankAccount targetAccount, double amount) {
		super(null, targetAccount, amount, new java.util.Date ());
		
		
	}

}
