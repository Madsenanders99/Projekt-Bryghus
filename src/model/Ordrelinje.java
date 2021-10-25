package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Ordrelinje {

	private int id;
	private int antal;
	private double rabat;
	private Produkt produkt;

	private static AtomicInteger idIncrement = new AtomicInteger();
	
	public Ordrelinje(int antal, Produkt produkt) {
		id = idIncrement.incrementAndGet();
	}
	// 0..1 Association til Produkt



	public Produkt getProdukt() {
		return produkt;
	}

	public void setProdukt(Produkt produkt) {
		if (this.produkt != produkt) {
			this.produkt = produkt;
		}
	}

	public int getAntal() {
		return antal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAntal(int antal) {
		this.antal = antal;
	}

	public double getRabat() {
		return rabat;
	}

	public void setRabat(double rabat) {
		this.rabat = rabat;
	}
}
