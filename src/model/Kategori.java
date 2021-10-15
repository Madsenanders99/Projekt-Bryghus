package model;

import java.util.ArrayList;

public class Kategori {

	private int id;
	private String navn;
	private String beskrivelse;
	private ArrayList<Produkt> produkter = new ArrayList<>();
	private ArrayList<Kategori> kategorier = new ArrayList<>();
	
	public Kategori(String navn) {
		
	}
	// 0..* association til produkt
	public ArrayList<Produkt> getProdukter() {
		return new ArrayList<>(produkter);
	}

	public Produkt createProdukt (String navn, AktivPrisliste aktivPrisliste) {
		Produkt produkt = new Produkt (navn, aktivPrisliste);
		produkter.add(produkt);
		return produkt;
	}
	public void addProdukt (Produkt produkt) {
		if (!produkter.contains(produkt)) {
			produkter.add(produkt);
		}
	}

	public void removeProdukt(Produkt produkt){
		if (produkter.contains(produkt)) {
			produkter.remove(produkt);
		}
	}

	// 0..* association til Kategori
	public ArrayList<Kategori> getKategorier() {
		return new ArrayList<>(kategorier);
	}

	public Kategori createKategori (String navn) {
		Kategori kategori = new Kategori (navn);
		kategorier.add(kategori);
		return kategori;
	}
	public void addKategori (Kategori kategori) {
		if (!kategorier.contains(kategori)) {
			kategorier.add(kategori);
		}
	}
	public void removeKategori (Kategori kategori) {
		if (kategorier.contains(kategori)) {
			kategorier.remove(kategori);
		}
	}
//	addProdukt(produkt){
//
//	}
//
//	removeProdukt(produkt){
//
//	}
//
//	addKategori(kategori){
//
//	}
//
//	removeKategori(kategori){
//
//	}
}
