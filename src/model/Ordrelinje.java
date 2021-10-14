package model;

public class Ordrelinje {

	private int id;
	private int antal;
	private double rabat;
	
	
	public Ordrelinje() {
		
	}
	// 0..1 Association til Produkt

	private Produkt produkt;

	public Produkt getProdukt() {
		return produkt;
	}

	public void setProdukt(Produkt produkt) {
		if (this.produkt != produkt) {
			this.produkt = produkt;
		}
	}
}
