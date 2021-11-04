package model;

import java.time.LocalDate;

public class Udlejning {

	private LocalDate datoStart;
	private LocalDate datoSlut;
	private LocalDate datoAfleveret;
	private Ordre ordre;
	
	public Udlejning(LocalDate datoStart,LocalDate datoSlut, Ordre ordre) {
		this.datoStart=datoStart;
		this.datoSlut=datoSlut;
		this.ordre=ordre;
	}



	public double findPrisIkkeReturneret() {
		for (int i = 0; i < getOrdre().getOrdrelinjer().size(); i++) {
			if (getOrdre().getOrdrelinjer().get(i).isErUdlejning()) {
				if (getOrdre().getOrdrelinjer().get(i).getPris().isAfregnesVedReturnering()) {
					int antal = getOrdre().getOrdrelinjer().get(i).getAntal();
					double pris = getOrdre().getOrdrelinjer().get(i).getPris().getPris();
					int pant = getOrdre().getOrdrelinjer().get(i).getPris().getPant();
					return (pris - pant) * antal;
				}
			}
		}
		return 0;
	}

	public double findPrisReturneret() {
		for (int i = 0; i < getOrdre().getOrdrelinjer().size(); i++) {
			if (getOrdre().getOrdrelinjer().get(i).isErUdlejning()) {
				if (getOrdre().getOrdrelinjer().get(i).getPris().isAfregnesVedReturnering()) {
					int antal = getOrdre().getOrdrelinjer().get(i).getAntal();
					double pris = getOrdre().getOrdrelinjer().get(i).getPris().getPris();
					int pant = getOrdre().getOrdrelinjer().get(i).getPris().getPant();
					setDatoAfleveret(LocalDate.now());
					return (pris - pant * 2) * antal;
				}
			}
		}
		return 0;
	}
	public double findPrisReturneretIkkeÅbnet() {
		for (int i = 0; i < getOrdre().getOrdrelinjer().size(); i++) {
			if (getOrdre().getOrdrelinjer().get(i).isErUdlejning()) {
				if (getOrdre().getOrdrelinjer().get(i).getPris().isAfregnesVedReturnering()) {
					int antal = getOrdre().getOrdrelinjer().get(i).getAntal();
					int pant = getOrdre().getOrdrelinjer().get(i).getPris().getPant();
					setDatoAfleveret(LocalDate.now());
					return (- pant) * antal;
				}
			}
		}
		return 0;
	}

	// 1 Association til Ordre
	Ordre getOrdre() {
		return ordre;
	}

	//
	LocalDate getDatoStart() {
		return datoStart;
	}
	LocalDate getDatoSlut() {
		return datoSlut;
	}
	LocalDate getDatoAfleveret() {
		return datoAfleveret;
	}


	public void setDatoStart(LocalDate datoStart) {
		this.datoStart = datoStart;
	}

	public void setDatoSlut(LocalDate datoSlut) {
		this.datoSlut = datoSlut;
	}

	public void setDatoAfleveret(LocalDate datoAfleveret) {
		this.datoAfleveret = datoAfleveret;
	}
}
