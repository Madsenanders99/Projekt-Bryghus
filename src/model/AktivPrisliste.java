package model;

public class AktivPrisliste {

	private Prisliste prisliste;

	public AktivPrisliste (Prisliste prisliste) {

	}
	// association --> 1 Prisliste
	public Prisliste getPrisliste() {
		return prisliste;
	}

	public void setPrisliste(Prisliste prisliste) {
		if(this.prisliste != prisliste) {
			this.prisliste = prisliste;
		}
	}
	
}