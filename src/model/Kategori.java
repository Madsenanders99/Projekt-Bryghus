package model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Kategori {

	private int id;
	private String navn;
	private String beskrivelse;
	private final ArrayList<Pris> priser = new ArrayList<>();
	private static AtomicInteger idIncrement = new AtomicInteger();

	public Kategori(String navn) {
		this.navn = navn;
		id = idIncrement.incrementAndGet();

	}
	// 0..* association til Pris
	public ArrayList<Pris> getPriser () {
		return new ArrayList<>(priser);
	}

	public void addPris (Pris pris) {
		if (!priser.contains(pris)) {
			priser.add(pris);
			pris.addKategori(this);
		}
	}

	public void removePris (Pris pris) {
		if (priser.contains(pris)) {
			priser.remove(pris);
			pris.removeKategori(this);
		}
	}

		public String getNavn() {
		return navn;
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

}
