package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Ordrelinje {

	private int id;
	private int antal;
	private double rabat;
	private Pris pris;
	private static AtomicInteger idIncrement = new AtomicInteger();
	private boolean erUdlejning;
	
	public Ordrelinje(Pris pris, int antal)
	{
		this.pris = pris;
		this.antal = antal;
		id = idIncrement.incrementAndGet();
	}

	public Pris getPris() {
		return pris;
	}

	public double getSamletPris() {
		return getPris().getPris() * antal * getRegnbarRabat();
	}

	public void setPris(Pris pris) {
		this.pris = pris;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAntal() {
		return antal;
	}

	public void setAntal(int antal) {
		this.antal = antal;
	}

	public double getRegnbarRabat() {
		return 1 - (rabat * 0.01);
	}

	public double getRabat () {
		return rabat;
	}

	public void setRabat(double rabat) {
		this.rabat = rabat;
	}

	public boolean isErUdlejning() {
		return erUdlejning;
	}

	public void setErUdlejning(boolean erUdlejning) {
		this.erUdlejning = erUdlejning;
	}
}
