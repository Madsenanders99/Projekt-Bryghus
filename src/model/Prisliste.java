package model;

import java.util.ArrayList;

public class Prisliste {

	private String navn;
	private boolean tilladKlip;
	private final ArrayList<Pris> priser = new ArrayList<>();
	// Constructor
	public Prisliste(String navn)
	{
		this.navn = navn;
		this.tilladKlip = false;
	}

	public Prisliste(String navn, boolean tilladKlip)
	{
		this.navn = navn;
		this.tilladKlip = tilladKlip;
	}

	// aggregering --> 0..* Pris
	public Pris createPris (Produkt produkt, double prisen, int klip) {
		Pris pris = new Pris(produkt, prisen, klip);
		priser.add(pris);
		return pris;
	}

	public ArrayList<Pris> getPriser() {
		return new ArrayList<>(priser);
	}

	// setters og getters

	public boolean getTilladKlip()
	{
		return this.tilladKlip;
	}

	public void setTilladKlip(boolean tilladKlip)
	{
		this.tilladKlip = tilladKlip;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getNavn() {
		return navn;
	}

	// toString metode
	@Override
	public String toString() {
		return navn;
	}
}
