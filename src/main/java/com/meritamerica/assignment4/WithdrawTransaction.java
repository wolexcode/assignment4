package com.meritamerica.assignment4;

public class WithdrawTransaction extends Transaction {
	WithdrawTransaction(BankAccount targetAccount, double amount) {
		super(null, targetAccount, amount, new java.util.Date ());
	}

	@Override
	public void process()
			throws NegativeAmountException, 
			ExceedsAvailableBalanceException, 
			ExceedsFraudSuspicionLimitException {
		if (getAmount() < 0) throw new NegativeAmountException();
		if (getAmount() > getTargetAccount().getBalance()) throw new ExceedsAvailableBalanceException();
		if (getAmount() > getFraudLimit()) throw new ExceedsFraudSuspicionLimitException();
		getTargetAccount().withdraw(getAmount());
	}
}
