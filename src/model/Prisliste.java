package model;

import java.util.ArrayList;

public class Prisliste {

	private String navn;
	// aggregering --> 0..* Pris



	private ArrayList<Pris> priser = new ArrayList<>();

	public ArrayList<Pris> getPriser() {
		return new ArrayList<>(priser);
	}
	public Pris createPris (Produkt produkt) {
		Pris pris = new Pris(produkt);
		priser.add(pris);
		return pris;
	}
	public void setNavn(String navn) {
		this.navn = navn;
	}
	public Prisliste(String navn) {

	}
	public String getNavn() {
		return navn;
	}
}
