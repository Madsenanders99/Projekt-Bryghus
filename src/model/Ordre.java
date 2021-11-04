package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Ordre {

	private int id;
	private LocalDateTime dato;
	private LocalDate betalt;
	private Kunde kunde;
	private final ArrayList<Ordrelinje> ordrelinjer = new ArrayList<>();
	private static AtomicInteger idIncrement = new AtomicInteger();
	private Udlejning udlejning;

	// Constructor
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

	public Ordrelinje createOrdrelinje(Pris pris, int antal) {
		Ordrelinje ol = new Ordrelinje(pris, antal);
		// Ordre tjekker om det er en udlejning, afregnesVedReturnering er null hvis det ikke er en udlejning
		if (ol.getPris().isAfregnesVedReturnering() == true || ol.getPris().isAfregnesVedReturnering() == false) {
			ol.setErUdlejning(true);
			this.ordrelinjer.add(ol);
			createUdlejning(LocalDate.now(), LocalDate.now().plusDays(3), this);
			return ol;
		}
			this.ordrelinjer.add(ol);
			return ol;
		}
	// 0..1 Association til Udlejning
	public Udlejning createUdlejning(LocalDate datoStart,LocalDate datoSlut, Ordre ordre) {
		Udlejning Ud = new Udlejning( datoStart, datoSlut, ordre);
		this.setUdlejning(Ud);
		return Ud;
	}

	public Udlejning getUdlejning() {
		return udlejning;
	}

	public void setUdlejning(Udlejning udlejning) {
		this.udlejning = udlejning;
	}

	// finder diverse totale på hele ordren
	public double findTotalPris() {
		double endeligPris = 0;
		for (int i = 0; i < getOrdrelinjer().size(); i++) {
			double rabatPris;
			double tempPris;
			tempPris = getOrdrelinjer().get(i).getPris().getPris();
			rabatPris = tempPris * getOrdrelinjer().get(i).getRegnbarRabat();
			endeligPris = endeligPris + rabatPris * getOrdrelinjer().get(i).getAntal();
		}
		return endeligPris;
	}

	public int findTotalKlip()
	{
		int totalKlip = 0;
		for (Ordrelinje ol : this.ordrelinjer) {
			totalKlip += ol.getSamletKlip();
		}
		return totalKlip;
	}

	public double findTotalKlipKrVærdi()
	{
		double totalKlipVaerdi = 0;
		for (Ordrelinje ol : this.ordrelinjer) {
			if (ol.getSamletKlip() != 0) totalKlipVaerdi += ol.getSamletPris();
		}
		return totalKlipVaerdi;
	}
	// returner udlejning hvis udlejning ikke er afleveret inden for tidsrummet, returner ellers null
	public Udlejning findIkkeAfleveredeProdukt() {
		if (this.getUdlejning() != null) {
			if (this.getUdlejning().getDatoAfleveret() == null) {
				long diff = ChronoUnit.DAYS.between(getUdlejning().getDatoStart(), getUdlejning().getDatoSlut());
				long diff2 = ChronoUnit.DAYS.between(getUdlejning().getDatoStart(), LocalDate.now());
				if (diff2 > diff) {
					return this.getUdlejning();
				} else {
					return null;
				}
			}
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public LocalDateTime getDato() {
		return dato;
	}

	public LocalDate getBetalt() {
		return betalt;
	}

	public void setBetalt(LocalDate betalt) {
		this.betalt = betalt;
	}

}
