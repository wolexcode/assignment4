package com.meritamerica.assignment4;

public class DepositTransaction extends Transaction {

	DepositTransaction(BankAccount targetAccount, double amount) {
		super(null, targetAccount, amount, new java.util.Date());
	}

}
