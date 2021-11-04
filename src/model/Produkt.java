package model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Produkt {

	private int id;
	private String navn;
	private String beskrivelse;
	private ArrayList<Produkt> produkter = new ArrayList<>();
	private static AtomicInteger idIncrement = new AtomicInteger();
	// Constructor
	public Produkt(String navn) {
		this.navn = navn;
		id = idIncrement.incrementAndGet();
		//System.out.println(id);
	}


	// setters og getters
	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	public void setProdukter(ArrayList<Produkt> produkter) {
		this.produkter = produkter;
	}

	public int getId() {
		return id;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}
}
