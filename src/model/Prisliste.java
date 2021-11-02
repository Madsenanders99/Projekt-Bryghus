package model;

import java.util.ArrayList;

public class Prisliste {

	private String navn;

	public Prisliste(String navn) {
		this.navn = navn;
	}

	// aggregering --> 0..* Pris



	private ArrayList<Pris> priser = new ArrayList<>();

	public ArrayList<Pris> getPriser() {
		return new ArrayList<>(priser);
	}
	public Pris createPris (Produkt produkt, double prisen, int klip) {
		Pris pris = new Pris(produkt, prisen, klip);
		priser.add(pris);
		return pris;
	}
	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getNavn() {
		return navn;
	}

	@Override
	public String toString() {
		return navn;
	}
}
