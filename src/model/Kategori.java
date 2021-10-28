package model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Kategori {

	private int id;
	private String navn;
	private String beskrivelse;
	private ArrayList<Produkt> produkter = new ArrayList<>();

	private static AtomicInteger idIncrement = new AtomicInteger();

	public Kategori(String navn) {
		this.navn = navn;
		id = idIncrement.incrementAndGet();

	}
	public String getNavn() {
		return navn;
	}
	// 0..* association til produkt
	public ArrayList<Produkt> getProdukter() {
		return new ArrayList<>(produkter);
	}

	public Produkt createProdukt (String navn) {
		Produkt produkt = new Produkt (navn);
		produkter.add(produkt);
		produkt.addKategori(this);
		return produkt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	public void setProdukter(ArrayList<Produkt> produkter) {
		this.produkter = produkter;
	}

	public void addProdukt (Produkt produkt) {
		if (!produkter.contains(produkt)) {
			produkt.addKategori(this);
			produkter.add(produkt);
		}
	}

	public void removeProdukt(Produkt produkt){
		if (produkter.contains(produkt)) {
			produkt.addKategori(this);
			produkter.remove(produkt);
		}
	}


}
