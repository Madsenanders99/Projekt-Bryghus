package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Ordre {

	private int id;
	private LocalDateTime dato;
	private double totalPris;
	private LocalDate betalt;
	private Kunde kunde;
	private final ArrayList<Ordrelinje> ordrelinjer = new ArrayList<>();

	private static AtomicInteger idIncrement = new AtomicInteger();

	public Ordre (LocalDateTime dato) {
		this.dato = dato;
		id = idIncrement.incrementAndGet();
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

	public LocalDate getBetalt() {
		return betalt;
	}

	public void setBetalt(LocalDate betalt) {
		this.betalt = betalt;
	}

	public void findTotalPris() {
		double endeligPris = 0;
		for (int i = 0; i < getOrdrelinjer().size(); i++) {
			double rabatPris;
			double tempPris;
			tempPris = getOrdrelinjer().get(i).getPris().getPris();
			rabatPris = tempPris * getOrdrelinjer().get(i).getRabat();
			endeligPris = endeligPris + rabatPris * getOrdrelinjer().get(i).getAntal();
		}
		totalPris = endeligPris;
	}
//	public void findTotalPris () {
//		double endeligPris = 0;
//		for (int i = 0; i < getOrdrelinjer().size(); i++) {
//			double tempPris;
//			double rabatPris;
//			Produkt produkt = getOrdrelinjer().get(i).getProdukt();
//			Prisliste prisliste = produkt.getAktivPrisliste().getPrisliste();
//			tempPris = prisliste.getPriser().get(getOrdrelinjer().get(i).getProdukt().getId()).getPris();
//			rabatPris = tempPris * getOrdrelinjer().get(i).getRabat();
//			endeligPris = endeligPris + rabatPris * getOrdrelinjer().get(i).getAntal();
//		}
//		totalPris = endeligPris;
//	}
}
