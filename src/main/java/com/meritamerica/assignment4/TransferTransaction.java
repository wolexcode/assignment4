package com.meritamerica.assignment4;

public class TransferTransaction extends Transaction {

	TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount) {
		
		super(sourceAccount, targetAccount, amount, new java.util.Date());
	}

}
