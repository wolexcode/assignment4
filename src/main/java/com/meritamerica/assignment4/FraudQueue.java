package com.meritamerica.assignment4;

import java.util.ArrayList;
import java.util.List;

public class FraudQueue {
	List<Transaction> que;
	
	
	
	FraudQueue() {
		this.que = new ArrayList<Transaction>();
		
		
	}
	
	public void addTransaction(Transaction transaction) {
		que.add(transaction);
			
	}
	public Transaction getTransaction() {
		Transaction transaction = que.get(0);
		que.remove(transaction);
		return transaction;
		
	}
	
	
}
