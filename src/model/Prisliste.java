package model;

import java.util.HashMap;

public class Prisliste {
 
	private String navn;
	import java.util.HashMap;
	import java.util.Map;
	public class Prisliste {
	// aggregering --> 0..* Pris
	
	private String navn;
	
	
	private HashMap<Integer, Double> priser = new HashMap<>();

	public Prisliste(String navn) {

	}

	public void addPris(int produktID,Double pris) {
		if(!priser.containsKey(produktID) && !priser.containsValue(pris)) {
			priser.put(produktID, pris);
		}
	}

	public void removePris(int produktID,double pris) {
		if(priser.containsKey(produktID) && priser.containsValue(pris)) {
			priser.remove(produktID, pris);
		}
	}

	public String getNavn() {
		return navn;
	}
}
