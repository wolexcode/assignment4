package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckingAccount extends BankAccount {

	private final static double INTEREST_RATE = 0.0001;
	
	public CheckingAccount(double openingBalance) {
		super(openingBalance, INTEREST_RATE);
	}

	public CheckingAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn) {
		super(accountNumber, balance, interestRate, accountOpenedOn);
	}

	public static CheckingAccount readFromString(String bankAccount) throws ParseException {
		String[] input = bankAccount.split(",");
		long accountNumber = Long.parseLong(input[0]);
		double balance = Double.parseDouble(input[1]);
		double interestRate = Double.parseDouble(input[2]);
		java.util.Date accountOpenedOn = new SimpleDateFormat("dd/MM/yyyy").parse(input[3]);
		return new CheckingAccount(accountNumber, balance, interestRate, accountOpenedOn);
	}
}
