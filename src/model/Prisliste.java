package model;

import java.util.ArrayList;

public class Prisliste {

	private String navn;
	private boolean tilladKlip;
	private final ArrayList<Pris> priser = new ArrayList<>();

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

	@Override
	public String toString() {
		return navn;
	}

	public boolean getTilladKlip()
	{
		return this.tilladKlip;
	}

	public void setTilladKlip(boolean tilladKlip)
	{
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


	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getNavn() {
		return navn;
	}

}
