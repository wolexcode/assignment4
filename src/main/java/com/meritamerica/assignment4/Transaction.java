package com.meritamerica.assignment4;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public abstract class Transaction {

	private BankAccount sourceAccount;
	private BankAccount targetAccount;
	private double amount;
	private java.util.Date transactionDate;
	private String rejectionReason;
	private boolean processed;
	private static final double FRAUD_LIMIT = 1000;
    
    Transaction(BankAccount sourceAccount, BankAccount targetAccount, double amount, java.util.Date transactionDate) {
    	this.sourceAccount = sourceAccount;
    	this.targetAccount = targetAccount;
    	this.amount = amount;
    	this.transactionDate = transactionDate;
    }
    
    public double getFraudLimit() {
    	return FRAUD_LIMIT;
    }
	
	public BankAccount getSourceAccount() {
		return sourceAccount;
		
	}
	public void setSourceAccount(BankAccount sourceAccount) {
		this.sourceAccount = sourceAccount;
		
	}
	public BankAccount getTargetAccount() {
		return targetAccount;
		
	}
	public void setTargetAccount(BankAccount targetAccount) {
		this.targetAccount = targetAccount;
		
	}
	public double getAmount() {
		return amount;
		
	}
	public void setAmount(double amount) {
		this.amount = amount;
		
	}
	public java.util.Date getTransactionDate() {
		return transactionDate;
		
	}
	public void setTransactionDate(java.util.Date date) {
		this.transactionDate = date;
		
	}
	public String writeToString() {
		StringBuilder result = new StringBuilder();
		
		if  (sourceAccount != null) {
			
			//to add something to a StringBuilder we use ".append"
			result.append(sourceAccount.getAccountNumber());
				
		}
		
		else {
			result.append("-1");
			
		}
		result.append(",");
		
		result.append(targetAccount.getAccountNumber());
		result.append(",");
		result.append(amount);
		result.append(",");
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		result.append(df.format(transactionDate));
		
		return result.toString();
		
	}
	
	
	public static Transaction readFromString(String transactionDataString) throws ParseException {
		String[] input = transactionDataString.split(",");
		long sourceAccountId = Long.parseLong(input[0]);
		BankAccount sourceAccount = MeritBank.getBankAccount(sourceAccountId);
		long targetAccountId = Long.parseLong(input[1]);
		BankAccount targetAccount = MeritBank.getBankAccount(targetAccountId);
		double Amount = Double.parseDouble(input[2]);
		java.util.Date accountOpenedOn = new SimpleDateFormat("dd/MM/yyyy").parse(input[3]);
		
		if (sourceAccount != null) {
			
			TransferTransaction edition = new TransferTransaction(sourceAccount, targetAccount, Amount);
			edition.setTransactionDate(accountOpenedOn);
			return edition;
			
		}
		if (Amount > 0) {
			DepositTransaction transaction = new DepositTransaction(targetAccount, Amount);
			transaction.setTransactionDate(accountOpenedOn);
			return transaction;
			
		}
		WithdrawTransaction withdrawTransaction = new WithdrawTransaction(targetAccount, Amount);
		withdrawTransaction.setTransactionDate(accountOpenedOn);
		return withdrawTransaction;
	
	}
	public abstract void process() throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException;
		
	public boolean isProcessedByFraudTeam() {
		return processed;
		
	}
	public void setProcessedByFraudTeam(boolean isProcessed) {
		processed = isProcessed;
	}
	public String getRejectionReason() {
		return rejectionReason;
		
	}
	public void setRejectionReason(String reason) {
		rejectionReason = reason;
		
	}

	
	
	
}
