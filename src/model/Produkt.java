package model;

import java.util.ArrayList;

public class Produkt {

	private int id;
	private String navn;
	private String beskrivelse;
	private AktivPrisliste aktivPrisliste;
	private ArrayList<Produkt> produkter = new ArrayList<>();
	
	public Produkt(String navn, AktivPrisliste aktivPrisliste) {

	}

	public String getNavn() {
		return navn;
	}
	// 1 association fra Produkt til AktivPrisliste

	public AktivPrisliste getAktivPrisliste() {
		return aktivPrisliste;
	}

	// 0..* association til Produkt
	public ArrayList<Produkt> getProdukter() {
		return new ArrayList<>(produkter);
	}

	public Produkt createProdukt (String navn, AktivPrisliste aktivPrisliste) {
		Produkt produkt = new Produkt (navn, aktivPrisliste);
		produkter.add(produkt);
		return produkt;
	}
	public void addProdukt(Produkt produkt) {
		if (!produkter.contains(produkt)) {
			produkter.add(produkt);
		}
	}
	public void removeProdukt (Produkt produkt) {
		if (produkter.contains(produkt)) {
			produkter.remove(produkt);
		}
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	public void setAktivPrisliste(AktivPrisliste aktivPrisliste) {
		this.aktivPrisliste = aktivPrisliste;
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
