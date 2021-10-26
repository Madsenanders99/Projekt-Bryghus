package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Ordrelinje {

	private int id;
	private int antal;
	private double rabat;
	private Pris pris;

	private static AtomicInteger idIncrement = new AtomicInteger();
	
	public Ordrelinje(int antal, Pris pris)
	{
		this.pris = pris;
		this.antal = antal;
		id = idIncrement.incrementAndGet();
	}
	// 0..1 Association til Produkt



	public Pris getPris() {
		return pris;
	}

	public void setPris(Pris pris) {
		if (this.pris != pris) {
			this.pris = pris;
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
		return 1 - (rabat * 0.01);
	}

	public void setRabat(double rabat) {
		this.rabat = rabat;
	}
}
