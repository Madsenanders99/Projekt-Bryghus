package model;

import java.time.LocalDate;

public class Udlejning {

	private LocalDate datoStart;
	private LocalDate datoSlut;
	private LocalDate datoAfleveret;
	private int depositum;
	private Ordre ordre;
	
	public Udlejning(LocalDate datoStart,LocalDate datoSlut, int depositum, Ordre ordre) {
		this.datoStart=datoStart;
		this.datoSlut=datoSlut;
		this.depositum=depositum;
		this.ordre=ordre;
	}
	
	LocalDate getDatoStart() {
		return datoStart;
	}
	LocalDate getDatoSlut() {
		return datoSlut;
	}
	LocalDate getDatoAfleveret() {
		return datoAfleveret;
	}
	int getDepositum() {
		return depositum;
	}
	Ordre getOrdre() {
		return ordre;
	}
}
