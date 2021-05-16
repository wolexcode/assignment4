package com.meritamerica.assignment4;

import java.util.*;

public class FraudQueue {
	private List<Transaction> queue;
	
	FraudQueue() {
		queue = new ArrayList<Transaction>();
	}

	public void addTransaction(Transaction transaction) {
		
	}
	
	public Transaction getTransaction() {
		Transaction transaction = queue.get(0);
		queue.remove(transaction);
		return transaction;
		
	}

}
