package com.meritamerica.assignment4;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public abstract class Transaction {


	
	BankAccount SourceAccount;
	BankAccount TargetAccount;
    double Amount;
    java.util.Date TransactionDate;
    String RejectionReason;
    boolean processed;
    
    Transaction(BankAccount sourceAccount, BankAccount targetAccount, double Amount, java.util.Date transactionDate) {
    	SourceAccount = sourceAccount;
    	TargetAccount = targetAccount;
    	this.Amount = Amount;
    	TransactionDate = transactionDate;
    }
    
	
	public BankAccount getSourceAccount() {
		return SourceAccount;
		
	}
	public void setSourceAccount(BankAccount sourceAccount) {
		SourceAccount = sourceAccount;
		
	}
	public BankAccount getTargetAccount() {
		return TargetAccount;
		
	}
	public void setTargetAccount(BankAccount targetAccount) {
		TargetAccount = targetAccount;
		
	}
	public double getAmount() {
		return Amount;
		
	}
	public void setAmount(double amount) {
		Amount = amount;
		
	}
	public java.util.Date getTransactionDate() {
		return TransactionDate;
		
	}
	public void setTransactionDate(java.util.Date date) {
		TransactionDate = date;
		
	}
	public String writeToString() {
		StringBuilder result = new StringBuilder();
		
		if  (SourceAccount != null) {
			
			//to add something to a StringBuilder we use ".append"
			result.append(SourceAccount.getAccountNumber());
				
		}
		
		else {
			result.append("-1");
			
		}
		result.append(",");
		
		result.append(TargetAccount.getAccountNumber());
		result.append(",");
		result.append(Amount);
		result.append(",");
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		result.append(df.format(TransactionDate));
		
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
			return new TransferTransaction(sourceAccount, targetAccount, Amount, accountOpenedOn);
		}
		if (Amount > 0) {
			DepositTransaction transaction = new DepositTransaction(targetAccount, Amount);
			transaction.setTransactionDate(accountOpenedOn);
			return transaction;
			
		}
		return new WithdrawTransaction(sourceAccount, targetAccount, Amount, accountOpenedOn);
	
	}
	public abstract void process() throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException;
		
	public boolean isProcessedByFraudTeam() {
		return processed;
		
	}
	public void setProcessedByFraudTeam(boolean isProcessed) {
		processed = isProcessed;
	}
	public String getRejectionReason() {
		return RejectionReason;
		
	}
	public void setRejectionReason(String reason) {
		RejectionReason = reason;
		
	}

	
	
	
}
