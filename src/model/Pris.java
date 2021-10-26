package model;

public class Pris {

	private int produktID;
	private double pris;
	private int klip;
	private Produkt produkt;
	
	public Pris(int produktID, double pris, int klip) {
		this.produktID = produktID;
		this.pris = pris;
		this.klip = klip;
	}
	
	public Pris(int produktID, double pris) {
		this.produktID = produktID;
		this.pris = pris;
	}
	
	public Pris(int produktID, int klip) {
		this.produktID = produktID;
		this.klip = klip;
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

	public void setProduktID(int produktID) {
		this.produktID = produktID;
	}

	public void setPris(double pris) {
		this.pris = pris;
	}

	public void setKlip(int klip) {
		this.klip = klip;
	}

	public Produkt getProdukt() {
		return produkt;
	}
}
