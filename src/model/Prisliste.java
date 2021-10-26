package model;

import java.util.ArrayList;


public class Prisliste {

	private String navn;
	// aggregering --> 0..* Pris



	private ArrayList<Pris> priser = new ArrayList<>();

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public ArrayList<Pris> getPriser() {
		return priser;
	}

	public void setPriser(ArrayList<Pris> priser) {
		this.priser = priser;
	}

	public Prisliste(String navn) {

	}

	public void addPris(Pris pris) {
		if (!priser.contains(pris)) {
			priser.add(pris);
		}
	}

	public void removePris(Pris pris) {
		if (priser.contains(pris)) {
			priser.remove(pris);
		}
	}

	public String getNavn() {
		return navn;
	}
}
