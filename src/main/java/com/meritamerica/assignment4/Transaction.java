package com.meritamerica.assignment4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public abstract class Transaction {


	
	BankAccount SourceAccount;
	BankAccount TargetAccount;
    double Amount;
    java.util.Date TransactionDate;
    String RejectionReason;
    boolean processed;
    
	
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
	
	
	public static Transaction readFromString(String transactionDataString) {
		
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
