package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ordre {

	private int id;
	private LocalDateTime dato;
	private double totalPris;
	private LocalDate betalt;
	private Kunde kunde;
	private final ArrayList<Ordrelinje> ordrelinjer = new ArrayList<>();

	public Ordre (LocalDateTime dato) {

	}

	// 0..1 Til Kunde association
	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		if (this.kunde != kunde) {
			Kunde oldKunde = this.kunde;
			if (oldKunde != null) {
				oldKunde.removeOrdre(this);
			}
			this.kunde = kunde;
			if (kunde != null) {
				kunde.addOrdre(this);
			}
		}
	}
	// 	0..* Til Ordrelinjer komposition association
	public ArrayList<Ordrelinje> getOrdrelinjer() {
		return new ArrayList<>(ordrelinjer);
	}

	public Ordrelinje createOrdrelinje (int antal, Produkt produkt) {
		Ordrelinje Ordrelinje = new Ordrelinje(antal, produkt);
		ordrelinjer.add(Ordrelinje);
		return Ordrelinje;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDato() {
		return dato;
	}

	public void setDato(LocalDateTime dato) {
		this.dato = dato;
	}

	public double getTotalPris() {
		return totalPris;
	}

	public void setTotalPris(double totalPris) {
		this.totalPris = totalPris;
	}

	public LocalDate getBetalt() {
		return betalt;
	}

	public void setBetalt(LocalDate betalt) {
		this.betalt = betalt;
	}
//	public double setTotalPris () {
//		int endeligPris = 0;
//		for ( int i = 0; i < getOrdrelinjer().size(); i++) {
//			getOrdrelinjer().get(i).getProdukt().getAktivPrisliste().getPrisliste()
//			getOrdrelinjer().get(i).getAntal();
//		}
//	}
}
