package model;

import java.util.ArrayList;

// stefen test
public class Pris {

	private double pris;
	private int klip;
	private Produkt produkt;
	private final ArrayList<Kategori> kategorier = new ArrayList<>();
	private int pant;
	private boolean afregnesVedReturnering;
	// Constructor
	public Pris(Produkt produkt, double pris, int klip) {
		this.produkt = produkt;
		this.pris = pris;
		this.klip = klip;
	}


	public void setPant(int pant) {
		this.pant = pant;
		setAfregnesVedReturnering(true); // pant sættes kun hvis det er en udlejning hvor pris betales ved returnering
	}

	public boolean isAfregnesVedReturnering() {
		if(getPant() > 0) {
			setAfregnesVedReturnering(true);
		} else {
			setAfregnesVedReturnering(false);
		}
		return afregnesVedReturnering;
	}

	public void setAfregnesVedReturnering(boolean afregnesVedReturnering) {
		this.afregnesVedReturnering = afregnesVedReturnering;
	}

 // 0..* --> Association til Kategori
	public ArrayList<Kategori> getKategorier () {
		return new ArrayList<>(kategorier);
	}

	public void addKategori (Kategori kategori) {
		if (!kategorier.contains(kategori)) {
			kategorier.add(kategori);
			kategori.addPris(this);
		}
	}

	public void removeKategori (Kategori kategori) {
		if (kategorier.contains(kategori)) {
			kategorier.remove(kategori);
			kategori.removePris(this);
		}
	}

	// 1 Association til Produkt
	public Produkt getProdukt() {
		return produkt;
	}

	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}

	// getters og setters
	public double getPris() {
		return pris;
	}

	public void setPris(double pris) {
		this.pris = pris;
	}

	public int getKlip() {
		return klip;
	}

	public void setKlip(int klip) {
		this.klip = klip;
	}

	public int getPant() {
		return pant;
	}

}
