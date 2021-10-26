package model;

public class Pris {

	private int produktID;
	private double pris;
	private int klip;
	private Produkt produkt;

	public Pris(Produkt produkt) {
		this.produkt = produkt;
	}

	public Produkt getProdukt() {
		return produkt;
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
}
