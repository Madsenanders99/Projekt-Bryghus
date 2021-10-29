package model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Produkt {

	private int id;
	private String navn;
	private String beskrivelse;
	private Pris pris;
	private ArrayList<Produkt> produkter = new ArrayList<>();
	private ArrayList<Kategori> kategorier = new ArrayList<>();

	private static AtomicInteger idIncrement = new AtomicInteger();

	public Produkt(String navn) {
		this.navn = navn;
		id = idIncrement.incrementAndGet();
		//System.out.println(id);
	}

	public String getNavn() {
		return navn;
	}

	// 0..* association til Produkt
	public ArrayList<Produkt> getProdukter() {
		return new ArrayList<>(produkter);
	}

	public Produkt createProdukt (String navn) {
		Produkt produkt = new Produkt (navn);
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

	// 1..* Association til Kategori
	public ArrayList<Kategori> getKategorier() {
		return new ArrayList<>(kategorier);
	}

	public void addKategori(Kategori kategori) {
		if (!kategorier.contains(kategori)) {
			kategorier.add(kategori);
			kategori.addProdukt(this);
		}
	}

	public void removeKategori(Kategori kategori) {
		if (this.kategorier.contains(kategori)) {
			kategori.removeProdukt(this);
			this.kategorier.remove(kategori);
		}
	}
	// 1..1 Association til Pris
	public Pris getPris() {
		return pris;
	}
	public void setPris(Pris pris) {
		this.pris = pris;
		pris.setProdukt(this);
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
