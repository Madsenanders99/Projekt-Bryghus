package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Ordrelinje {

	private int id;
	private int antal;
	private double rabat;
	private Produkt produkt;

	private static AtomicInteger idIncrement = new AtomicInteger();
	
	public Ordrelinje(Produkt produkt, int antal)
	{
		this.produkt = produkt;
		this.antal = antal;
		id = idIncrement.incrementAndGet();
	}
	// 0..1 Association til Produkt



	public Pris getPris() {
		return this.produkt.getPris();
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
		return 1 - (rabat * 0.01);
	}

	public Produkt getProdukt() {
		return produkt;
	}

	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}

	public void setRabat(double rabat) {
		this.rabat = rabat;
	}
}
