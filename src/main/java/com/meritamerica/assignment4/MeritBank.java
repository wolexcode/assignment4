package com.meritamerica.assignment4;

import java.io.*;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Random;

public class MeritBank {
	
	private static AccountHolder[] accounts = new AccountHolder[0];
	private static CDOffering[] cdOfferings = new CDOffering[0];
	private static long nextAccountNumber = 1;
	
	public static void addAccountHolder(AccountHolder accountHolder) {
		AccountHolder[] tmp = new AccountHolder[accounts.length+1];
		for (int i = 0; i<accounts.length; i++) {
			tmp[i] = accounts[i];
		}
		tmp[accounts.length] = accountHolder;
		accounts = tmp;
	}
	
	public static AccountHolder[] getAccountHolders() {
		return accounts;
	}
	
	public static CDOffering[] getCDOfferings() {
		return cdOfferings;
	}


	public static CDOffering getBestCDOffering(double depositAmount) {
		CDOffering bestOffer = null;
		double highestResult = 0;
		if (cdOfferings!=null) {
			for (CDOffering x: cdOfferings) {
				double xFutureValue = recursiveFutureValue(depositAmount, x.getTerm(), x.getInterestRate());
				if (xFutureValue > highestResult) {
					highestResult = xFutureValue;
					bestOffer = x;
				}
			}
		}
		return bestOffer;
	}
	
	public static CDOffering getSecondBestCDOffering(double depositAmount) {
		CDOffering bestOffer = null;
		CDOffering secondBestOffer = null;
		double highestResult = 0;
		double secondHighestResult = 0;
		if (cdOfferings!=null) {
			for (CDOffering x: cdOfferings) {
				double xFutureValue = recursiveFutureValue(depositAmount, x.getTerm(), x.getInterestRate());
				if (xFutureValue > highestResult) {
					secondHighestResult = highestResult;
					highestResult = xFutureValue;
					secondBestOffer = bestOffer;
					bestOffer = x;
					
				}
				else if (xFutureValue > secondHighestResult) {
					secondHighestResult = xFutureValue;
					secondBestOffer = x;
				}
			}
		}
		return secondBestOffer;
	}

	
	public static void setCDOfferings(CDOffering[] cdOfferings) {
		MeritBank.cdOfferings = cdOfferings;
	}
	
	public static void addCDOfferings(CDOffering cdOffering) {
		CDOffering[] tmp = new CDOffering[cdOfferings.length+1];
		for(int i = 0; i < cdOfferings.length; i++) {
			tmp[i] = cdOfferings[i];
		}
		tmp[cdOfferings.length] = cdOffering;
		MeritBank.cdOfferings = tmp;
	}
	
	public static void clearCDOfferings() {
		MeritBank.cdOfferings = null;
	}
	
	public static long getNextAccountNumber() {
		return nextAccountNumber++;
	}
	
	public static void setNextAccountNumber(long nextAccountNumber) {
		MeritBank.nextAccountNumber = nextAccountNumber;
	}
	public static AccountHolder[] sortAccountHolders() {
		java.util.Arrays.sort(accounts);
		return accounts;
		
	}
	public static double totalBalances() {
		double total = 0;
		for (AccountHolder x: accounts) {
			total += x.getCombinedBalance();
		}
		return total;
	}

	public static double futureValue(double presentValue, double interestRate, int term) {
		return presentValue * Math.pow(1 + interestRate, term);
	}
	
	public void writeToFile(String file) throws Exception {
		BufferedWriter out = openFileWriter(file);
		out.write(""+nextAccountNumber);
		
		out.write(""+cdOfferings.length);
		for (int i = 0; i < cdOfferings.length; i++) {
			out.write(cdOfferings[i].writeToString());
		}
		
		out.write(""+accounts.length);
		for (int i = 0; i < accounts.length; i++) {
			AccountHolder ah1 = accounts[i];
			
			out.write(""+ah1.getNumberOfCheckingAccounts());
			CheckingAccount[] tmpChecking = ah1.getCheckingAccounts();
			for (int j = 0; j < ah1.getNumberOfCheckingAccounts(); j++) {
				out.write(tmpChecking[j].writeToString());
			}
			
			out.write(""+ah1.getNumberOfSavingsAccounts());
			CheckingAccount[] tmpSavings = ah1.getCheckingAccounts();
			for (int j = 0; j < ah1.getNumberOfSavingsAccounts(); j++) {
				out.write(tmpSavings[j].writeToString());
			}
			
			out.write(""+ah1.getNumberOfCDAccounts());
			CheckingAccount[] tmpCD = ah1.getCheckingAccounts();
			for (int j = 0; j < ah1.getNumberOfCDAccounts(); j++) {
				out.write(tmpCD[j].writeToString());
			}
		}
		
	}
	public static boolean readFromFile(String file) {
		try {
			BufferedReader in = openFile(file);
			MeritBank.nextAccountNumber = Long.parseLong(in.readLine());
			
			cdOfferings = new CDOffering[0];
			int offeringNumber = Integer.parseInt(in.readLine());
			for (int i = 0; i < offeringNumber; i++) {
				addCDOfferings(CDOffering.readFromString(in.readLine()));
			}
			
			accounts = new AccountHolder[0];
			int accountNumber = Integer.parseInt(in.readLine());
			for (int i = 0; i < accountNumber; i++) {
				AccountHolder ah1 = AccountHolder.readFromString(in.readLine());
				
				int checkingNumber = Integer.parseInt(in.readLine());
				for (int j = 0; j < checkingNumber; j++) {
					ah1.addCheckingAccount(CheckingAccount.readFromString(in.readLine()));
				}
				
				int savingNumber = Integer.parseInt(in.readLine());
				for (int j = 0; j < savingNumber; j++) {
					ah1.addSavingsAccount(SavingsAccount.readFromString(in.readLine()));
				}
				
				int cdNumber = Integer.parseInt(in.readLine());
				for (int j = 0; j < cdNumber; j++) {
					ah1.addCDAccount(CDAccount.readFromString(in.readLine()));
				}
				addAccountHolder(ah1);
			}
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	private static BufferedReader openFile(String file) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return in;
	}

	private BufferedWriter openFileWriter(String file) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}
	
	public static BankAccount getBankAccount(long accountId) {
		for(AccountHolder a: accounts) {
			for (CheckingAccount ch: a.getCheckingAccounts()) {
				if (accountId ==ch.getAccountNumber()) return ch;
				
			}
		
			for (SavingsAccount ch: a.getSavingsAccounts()) {
				if (accountId ==ch.getAccountNumber()) return ch;
				
			}
			for (CDAccount ch: a.getCDAccounts()) {
				if (accountId ==ch.getAccountNumber()) return ch;
				
			}
		}
		return null;
	}
	//Add the this methods and Existing futureValue methods that used to call Math.pow() should now call this method

	public static double recursiveFutureValue(double amount, int years, double interestRate) {
		
		if (years==0)
			return amount;
		
		double balance = amount+amount*interestRate;
		
		return recursiveFutureValue(balance, years-1, interestRate);
		
	}
	
	public static boolean processTransaction(Transaction transaction) throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		if (transaction.getAmount()<0) {
			
			double withdrawAmount = -transaction.getAmount();
			
			if (withdrawAmount>transaction.getTargetAccount().getBalance()) {
			throw	new ExceedsAvailableBalanceException();
			
			}	
		
		}
		else if (transaction.getAmount()>1000) {
			throw new ExceedsFraudSuspicionLimitException();
					
		} 
		if (transaction.getSourceAccount()!=null) {
			if (transaction.getAmount()<0) {
				throw new NegativeAmountException();
			}
			if (transaction.getAmount()>transaction.getSourceAccount().getBalance()) {
				throw new ExceedsAvailableBalanceException();
			}
			if (transaction.getAmount()>1000) {
				throw new ExceedsFraudSuspicionLimitException();
			}
		}
	
	
	}
	
}