package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Ordrelinje {

	private int id;
	private int antal;
	private double rabat;
	private Pris pris;
	private static AtomicInteger idIncrement = new AtomicInteger();
	private boolean erUdlejning;

	// Constructor
	public Ordrelinje(Pris pris, int antal)
	{
		this.pris = pris;
		this.antal = antal;
		id = idIncrement.incrementAndGet();
	}
	// 1 Association til Pris
	public Pris getPris() {
		return pris;
	}

	public void setPris(Pris pris) {
		this.pris = pris;
	}

	// Returner en rabat der let kan regnes på
	public double getRegnbarRabat() {
		return 1 - (rabat * 0.01);
	}

	// Udregner samlet pris/klip på ordrelinjen
	public double getSamletPris() {
		return getPris().getPris() * antal * getRegnbarRabat();
	}

	public double getSamletPrisUdenRabat() {
		return getPris().getPris() * antal;
	}

	public int getSamletKlip()
	{
		return this.pris.getKlip() * antal;
	}


	// setters og getters

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
