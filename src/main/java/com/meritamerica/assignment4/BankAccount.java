package com.meritamerica.assignment4;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {

	private double balance;
	private double interestRate = 0;
	private long accountNumber;
	private java.util.Date accountOpenedOn;
	private List<Transaction> transactions = new ArrayList<Transaction>();
	
	
	public BankAccount(double openingBalance, double interestRate, java.util.Date accountOpenedOn) {
	    accountNumber = MeritBank.getNextAccountNumber();
		balance = openingBalance;
		this.interestRate = interestRate;
		this.accountOpenedOn = accountOpenedOn;
		
	}

	public BankAccount(long accountNumber, double openingBalance, double interestRate, java.util.Date accountOpenedOn) {
		this.accountNumber = accountNumber;
		balance = openingBalance;
		this.interestRate = interestRate;
		this.accountOpenedOn = accountOpenedOn;
	}
	
	
	
	public BankAccount(double openingBalance, double interestRate) {
		accountNumber = MeritBank.getNextAccountNumber();
		balance = openingBalance;
		this.interestRate = interestRate;
		this.accountOpenedOn = new java.util.Date();
	}

	public BankAccount(long accountNumber, double openingBalance, double interestRate) {
		this.accountNumber = accountNumber;
		balance = openingBalance;
		this.interestRate = interestRate;
		this.accountOpenedOn = new java.util.Date();
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public double getInterestRate() {
		return interestRate;
	}
	
	boolean withdraw(double amount) {
		if (amount < 0 || amount > balance) {
			return false;
		}
		balance -= amount;
		return true;
	}

	boolean deposit(double amount) {
		if (amount < 0) {
			return false;
		}
		balance += amount;
		return true;
	}

	double futureValue(int years) {
		return balance * Math.pow(1 + interestRate, years);
	}

	@Override
	public String toString() {
		return "\nChecking Account Balance: $" + String.format("%.2f", this.getBalance())
				+ "\nChecking Account Interest Rate: " + String.format("%.4f", this.getInterestRate())
				+ "\nChecking Account Balance in 3 years: " + String.format("%.2f", this.futureValue(3));
	}

	public java.util.Date getOpenedOn() {
		return accountOpenedOn;
	}
	public static BankAccount readFromString(String bankAccount) throws ParseException {
		String[] input = bankAccount.split(",");
		long accountNumber = Long.parseLong(input[0]);
		double balance = Double.parseDouble(input[1]);
		double interestRate = Double.parseDouble(input[2]);
		java.util.Date accountOpenedOn = new SimpleDateFormat("dd/MM/yyyy").parse(input[3]);
		return new BankAccount(accountNumber, balance, interestRate, accountOpenedOn);
	}
	public String writeToString() {
		DateFormat df = new SimpleDateFormat("MM/dd/YYYY");
		return accountNumber + "," + balance + "," + interestRate + "," + df.format(accountOpenedOn);}


	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}

}