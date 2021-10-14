package model;

public class Pris {

	private int produktID;
	private double pris;
	private int klip;
	
	
	public Pris(int produktID, double pris, int klip) {
		
	}
	
	public Pris(int produktID, double pris) {
		
	}
	
	public Pris(double pris, int klip) {
		
	}
	public int getProduktID() {
		return produktID;
	}

	public double getPris() {
		return pris;
	}

	public int getKlip() {
		return klip;
	}
}
