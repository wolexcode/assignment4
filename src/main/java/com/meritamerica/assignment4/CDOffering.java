package com.meritamerica.assignment4;

public class CDOffering {
	private int term;
	private double interestRate;
	
	public CDOffering(int term, double interestRate) {
		this.term = term;
		this.interestRate = interestRate;
	}
	
	public int getTerm() {
		return term;
	}
	public double getInterestRate() {
		return interestRate;
	}
	
	public static CDOffering readFromString(String cdOfferingDataString) {
	String[] input = cdOfferingDataString.split(",");
	int term = Integer.parseInt(input[0]);
	double interestRate = Double.parseDouble(input[1]);
	return new CDOffering(term, interestRate);
	}
	public String writeToString() {
		return term + "," + interestRate;
		
	}
}
