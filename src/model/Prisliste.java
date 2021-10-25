package model;

import java.util.HashMap;
import java.util.Map;

public class Prisliste {

	private String navn;
	// aggregering --> 0..* Pris



	private HashMap<Integer, Pris> priser = new HashMap<>();

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public HashMap<Integer, Pris> getPriser() {
		return priser;
	}

	public void setPriser(HashMap<Integer, Pris> priser) {
		this.priser = priser;
	}

	public Prisliste(String navn) {

	}

	public void addPris(int produktID, Pris pris) {
		if (!priser.containsKey(produktID) && !priser.containsValue(pris)) {
			priser.put(produktID, pris);
		}
	}

	public void removePris(int produktID, Pris pris) {
		if (priser.containsKey(produktID) && priser.containsValue(pris)) {
			priser.remove(produktID, pris);
		}
	}

	public String getNavn() {
		return navn;
	}
}
