package com.meritamerica.assignment4;

public class TransferTransaction extends Transaction {

	TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount) {
		
		super(sourceAccount, targetAccount, amount, new java.util.Date());
	}

	@Override
	public void process()
			throws NegativeAmountException, 
			ExceedsAvailableBalanceException, 
			ExceedsFraudSuspicionLimitException {
		if (getAmount()<0) throw new NegativeAmountException();
		if (getAmount()>getSourceAccount().getBalance()) throw new ExceedsAvailableBalanceException();
		if (getAmount()>getFraudLimit()) throw new ExceedsFraudSuspicionLimitException();
		getSourceAccount().withdraw(getAmount());
		getTargetAccount().deposit(getAmount());
	}

}
