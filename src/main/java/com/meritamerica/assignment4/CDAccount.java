package com.meritamerica.assignment4;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CDAccount extends BankAccount {
	
	
	private int term;
	
	public CDAccount(CDOffering offering, double openingBalance) {
		super(openingBalance, offering.getInterestRate());
		term = offering.getTerm();

	}

	public CDAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn, int term) {
		super(accountNumber, balance, interestRate, accountOpenedOn);
		this.term = term;
	}

	public Date getStartDate() {
		return this.getOpenedOn();
	}

	public int getTerm() {
		return term;
	}
	
	@Override
	public boolean deposit(double amount) {
		return false;
	}
	
	@Override
	public boolean withdraw(double amount) {
		return false;
	}
	public static CDAccount readFromString(String bankAccount) throws ParseException {
		String[] input = bankAccount.split(",");
		long accountNumber = Long.parseLong(input[0]);
		double balance = Double.parseDouble(input[1]);
		double interestRate = Double.parseDouble(input[2]);
		java.util.Date accountOpenedOn = new SimpleDateFormat("dd/MM/yyyy").parse(input[3]);
		int term = Integer.parseInt(input[4]);
		return new CDAccount(accountNumber, balance, interestRate, accountOpenedOn, term);
		
	}

	double futureValue() {
		return getBalance() * Math.pow(1 + getInterestRate(), term);
	}
	
	public String writeToString() {
		DateFormat df = new SimpleDateFormat("MM/dd/YYYY");
		return getAccountNumber() + "," + getBalance() + "," + getInterestRate() + "," + df.format(getOpenedOn() + "," + term);}
}
