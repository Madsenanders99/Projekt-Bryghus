package model;

public class Pris {

	private double pris;
	private int klip;
	private Produkt produkt;

	public Pris(Produkt produkt, double pris, int klip) {
		this.produkt = produkt;
		this.pris = pris;
		this.klip = klip;
	}

	public Produkt getProdukt() {
		return produkt;
	}

	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}

	public double getPris() {
		return pris;
	}

	public int getKlip() {
		return klip;
	}

	public void setPris(double pris) {
		this.pris = pris;
	}

	public void setKlip(int klip) {
		this.klip = klip;
	}
}
